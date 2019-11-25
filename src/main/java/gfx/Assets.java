package gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width32 = 32, height32 = 32;
    private static final int width62 = 62, height62 = 62;


    public static BufferedImage player, dirt, grass, stone, sand, stone2, stone3;


    public static void init(){
        SpriteSheet playerSprites = new SpriteSheet(ImageLoader.loadImage("/textures/sprite_sheet.png"));
        SpriteSheet textures = new SpriteSheet(ImageLoader.loadImage("/textures/master-tileset.png"));

        player = playerSprites.crop(0, 0, width32, height32);
        stone = textures.crop(0, 0, width62, height62);
        stone2 = textures.crop(width62, 0, width62, height62);
        stone3 = textures.crop(width62 * 2, 0, width62, height62);
        sand = textures.crop(0, height32, width62, height62);
        grass = textures.crop(0, height62 * 2, width62, height62);
        dirt = textures.crop(0, height62, width62, height62);



    }

}

