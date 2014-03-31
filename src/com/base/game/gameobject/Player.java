/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject;

import com.base.engine.GameObject;
import com.base.game.gameobject.item.Item;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author sebastian
 */
public class Player extends GameObject
{
    public static final float SIZE = 32;
    
    
    private int level;
    private Stats stats;
    private Inventory inventory;
    
    public Player(float x, float y)
    {
        init(x,y,0.1f,1f,0.25f,SIZE,SIZE,PLAYER_ID);
        stats = new Stats(0, true);
        inventory = new Inventory(20);
    }
    @Override
    public void update()
    {
        //System.out.println("stats: SPEED: " + getSpeed() + " LEVEL: " + getLevel() + " MAXHP: " + getMaxHealth() + " HP: " +getCurrentHealth() + " STRENGTH: " + getStrength() +" MAGIC: " + getMagic());
    }
    
    public void getInput()
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_W))
            move(0,1);
        if(Keyboard.isKeyDown(Keyboard.KEY_S))
            move(0,-1);
        if(Keyboard.isKeyDown(Keyboard.KEY_A))
            move(-1,0);
        if(Keyboard.isKeyDown(Keyboard.KEY_D))
            move(1,0);
    }
    private void move(float magX, float magY)
    {
        x += getSpeed() * magX;
        y += getSpeed() * magY;
    }
    
        public void addItem(Item item)
    {
        inventory.add(item);
    }
        
    public float getSpeed()
    {
        return stats.getSpeed();
    }
    
    public int getLevel()
    {
       return stats.getLevel();
    }
    public int getMaxHealth()
    {
        return stats.getMaxHealth();
    }
    
    public int getCurrentHealth()
    {
       return stats.getCurrentHealth();
    }
    
    public float getStrength()
    {
        return stats.getStrength();
    }
    
    public float getMagic()
    {
        return stats.getMagic();
    }
    
    public void addXp(float amt)
    {
        stats.addXp(amt);
    }

}
