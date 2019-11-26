package gfx;

import java.awt.image.BufferedImage;

public class Assets {

    private static final int width32 = 32, height32 = 32;
    private static final int width62 = 62, height62 = 62;


    public static BufferedImage player, dirt, grass, stone, sand, stone2, stone3;
    public static BufferedImage[] playerDown, playerUp, playerLeft, playerRight;

    public static void init(){
        SpriteSheet playerSprites = new SpriteSheet(ImageLoader.loadImage("/textures/sprite_sheet.png"));
        SpriteSheet playerSprites2 = new SpriteSheet(ImageLoader.loadImage("/textures/walking_sprites_right.png"));
        SpriteSheet playerSpritesLeft = new SpriteSheet(ImageLoader.loadImage("/textures/walking_sprites_left.png"));


        SpriteSheet textures = new SpriteSheet(ImageLoader.loadImage("/textures/master-tileset.png"));

        player = playerSprites2.crop(0, 0, width32, height32);

        playerDown = new BufferedImage[2];
        playerUp = new BufferedImage[2];
        playerRight = new BufferedImage[2];
        playerLeft = new BufferedImage[2];
        playerDown[0] = playerSprites2.crop(width32 * 5, 0, width32, height32);
        playerDown[1] = playerSprites2.crop(width32 * 6, 0, width32, height32);
        playerUp[0] = playerSprites2.crop(width32 * 2, 0, width32, height32);
        playerUp[1] = playerSprites2.crop(width32 * 3, 0, width32, height32);
        playerRight[0] = playerSprites2.crop(width32 * 7, 0, width32, height32);
        playerRight[1] = playerSprites2.crop(width32 * 8, 0, width32, height32);
        playerLeft[0] = playerSpritesLeft.crop(width32, 0, width32, height32);
        playerLeft[1] = playerSpritesLeft.crop(width32 * 2, 0, width32, height32);



        stone = textures.crop(0, 0, width62, height62);
        stone2 = textures.crop(width62, 0, width62, height62);
        stone3 = textures.crop(width62 * 2, 0, width62, height62);
        sand = textures.crop(0, height32, width62, height62);
        grass = textures.crop(0, height62 * 2, width62, height62);
        dirt = textures.crop(0, height62, width62, height62);



    }

}

