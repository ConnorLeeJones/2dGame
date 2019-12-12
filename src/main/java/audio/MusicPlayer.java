package audio;

import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;

public class MusicPlayer implements Runnable {


    private ArrayList<String> musicFiles;
    private int currentSongIndex;

    public MusicPlayer(String... files) {
        this.musicFiles = new ArrayList<>();
        for(String file: files){
            musicFiles.add("/Users/connorjones/Documents/GitHub/Tile/src/res/audio/" + file + ".wav");
            //audio/GameMusic1.wav
            //Users/connorjones/Documents/GitHub/Tile/src/res/audio/GameMusic1.wav
            //Users/connorjones/Documents/GitHub/Tile/src/main/resources/audio/GameMusic1.wav
        }
    }


    private void playSound(String fileName){
        try {
            File soundFile = new File(fileName);
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = ais.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10);
            clip.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        while (true) {
            playSound(musicFiles.get(currentSongIndex));
        }
    }
}
