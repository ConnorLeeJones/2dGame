package inventory;

import gfx.Assets;
import items.Item;
import main.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Inventory {

    private Handler handler;
    private boolean active = false;
    private ArrayList<Item> inventoryItems;

    private int invX = 64, invY = 48,
        invWidth = 512, invHeight = 384,
        invListCenterX = invX + 171,
        getInvListCenterY = invY + invHeight / 2 + 5;

    public Inventory(Handler handler) {
        this.handler = handler;
        inventoryItems = new ArrayList<>();
    }

    public void tick(){
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E))
            active = !active;
        if(!active)
            return;

        System.out.println("Inventory");
        for(Item i : inventoryItems){
            System.out.println(i.getName() + "  " + i.getCount());
        }
    }

    public void render(Graphics g){
        if(!active)
            return;

        g.drawImage(Assets.inventoryScreen, invX, invY, invWidth, invHeight, null);
    }

    public void addItem(Item item){
        for (Item i : inventoryItems){
            if(i.getId() == item.getId()){
                i.setCount(i.getCount() + item.getCount());
                return;
            }
        }
        inventoryItems.add(item);
    }



    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }
}
