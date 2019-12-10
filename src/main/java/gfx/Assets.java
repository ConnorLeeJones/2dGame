package gfx;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Assets {

    private static final int width32 = 32, height32 = 32;
    private static final int width62 = 62, height62 = 62;

    public static Font font28, font15;



    public static BufferedImage player, dirt, grass, stone, sand, stone2, stone3, rock, tree, wood;
    public static BufferedImage[] playerDown, playerUp, playerLeft, playerRight;
    public static BufferedImage[] playerAttackLeft, playerAttackRight, playerAttackUp, playerAttackDown;
    public static BufferedImage[] startButton;
    public static BufferedImage inventoryScreen;
    public static BufferedImage healthPot, magicPot, jar;

    public static void init(){
        //SpriteSheet playerSprites = new SpriteSheet(ImageLoader.loadImage("/textures/sprite_sheet.png"));
        font28 = FontLoader.loadFont("src/res/fonts/slkscr.ttf", 28);
        font15 = FontLoader.loadFont("src/res/fonts/slkscr.ttf", 15);

        inventoryScreen = ImageLoader.loadImage("/textures/inventoryScreen.png");

        SpriteSheet playerSprites2 = new SpriteSheet(ImageLoader.loadImage("/textures/walking_sprites_right.png"));
        SpriteSheet playerSpritesLeft = new SpriteSheet(ImageLoader.loadImage("/textures/walking_sprites_left.png"));
        SpriteSheet entitySprites = new SpriteSheet(ImageLoader.loadImage("/textures/tree&rock.png"));
        SpriteSheet start = new SpriteSheet(ImageLoader.loadImage("/textures/start_button.png"));
        SpriteSheet attack = new SpriteSheet(ImageLoader.loadImage("/textures/attack_sprites.png"));
        SpriteSheet woodSheet = new SpriteSheet(ImageLoader.loadImage("/textures/wood.png"));
        SpriteSheet potionSheet = new SpriteSheet(ImageLoader.loadImage("/textures/potions.png"));
        SpriteSheet jarSheet = new SpriteSheet(ImageLoader.loadImage("/textures/jar.png"));



        SpriteSheet textures = new SpriteSheet(ImageLoader.loadImage("/textures/master-tileset.png"));

        player = playerSprites2.crop(0, 0, width32, height32);
        wood = woodSheet.crop(width32 * 2, 0, width32, height32);

        playerDown = new BufferedImage[2];
        playerUp = new BufferedImage[2];
        playerRight = new BufferedImage[2];
        playerLeft = new BufferedImage[2];
        startButton = new BufferedImage[2];
        playerAttackLeft = new BufferedImage[1];
        playerAttackRight = new BufferedImage[1];
        playerAttackUp = new BufferedImage[1];
        playerAttackDown = new BufferedImage[1];

        playerDown[0] = playerSprites2.crop(width32 * 5, 0, width32, height32);
        playerDown[1] = playerSprites2.crop(width32 * 6, 0, width32, height32);
        playerUp[0] = playerSprites2.crop(width32 * 2, 0, width32, height32);
        playerUp[1] = playerSprites2.crop(width32 * 3, 0, width32, height32);
        playerRight[0] = playerSprites2.crop(width32 * 7, 0, width32, height32);
        playerRight[1] = playerSprites2.crop(width32 * 8, 0, width32, height32);
        playerLeft[0] = playerSpritesLeft.crop(width32, 0, width32, height32);
        playerLeft[1] = playerSpritesLeft.crop(width32 * 2, 0, width32, height32);


        startButton[0] = start.crop(0, 0, width32 * 2, height32);
        startButton[1] = start.crop(width32 * 2, 0, width32 * 2, height32);

        playerAttackLeft[0] = attack.crop(0, 0, width32, height32);
        playerAttackRight[0] = attack.crop(width32, 0, width32, height32);
        playerAttackUp[0] = attack.crop(width32 * 3, 0, width32, height32);
        playerAttackDown[0] = attack.crop(width32 * 4, 0, width32, height32);



        healthPot = potionSheet.crop(width32 * 7, 0, width32, height32);
        magicPot = potionSheet.crop(width32 * 8, 0, width32, height32);
        jar = jarSheet.crop(width32 * 8, 0, width32, height32);

        rock = entitySprites.crop(width32 * 8, 0, width32, height32);
        tree = entitySprites.crop(width32 * 9, 0, width32, height32);


        stone = textures.crop(0, 0, width62, height62);
        stone2 = textures.crop(width62, 0, width62, height62);
        stone3 = textures.crop(width62 * 2, 0, width62, height62);
        sand = textures.crop(0, height32, width62, height62);
        grass = textures.crop(0, height62 * 2, width62, height62);
        dirt = textures.crop(0, height62, width62, height62);



    }

}

