package audio;

import javax.sound.sampled.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class MusicPlayer implements Runnable {


    private ArrayList<String> musicFiles;
    private int currentSongIndex;
    private Clip clip;

    private static String[] songs = {"WaterTheme1", "battle music 1", "GameMusic1", "overworld theme 1"};

    private static final MusicPlayer INSTANCE = new MusicPlayer(songs);




    public MusicPlayer(String... files) {
        this.musicFiles = new ArrayList<>();
        for(String file: files){
            musicFiles.add("/Users/connorjones/Documents/GitHub/Tile/src/res/audio/" + file + ".wav");
        }
    }

    public static MusicPlayer getINSTANCE() {
        return INSTANCE;
    }


    private void loopSound(String fileName){
        try {
            File soundFile = new File(fileName);
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = ais.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(ais);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10);
            this.clip = clip;
            //clip.start();
            //clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public void playMusic(String location){

        try {
            File musicPath = new File(location);

            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                //clip.
            } else {
                System.out.println("File not found");
            }

        } catch (Exception e){
            e.printStackTrace();
        }

    }


    @Override
    public void run() {
        //loopSound(musicFiles.get(currentSongIndex));
        //clip.close();
        loopSong();
    }

    public void loopSong(){
        loopSound(musicFiles.get(currentSongIndex));
        clip.start();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }



    public int getCurrentSongIndex() {
        return currentSongIndex;
    }

    public void setCurrentSongIndex(int currentSongIndex) {
        this.currentSongIndex = currentSongIndex;
        clip.stop();
        loopSong();
    }
}
