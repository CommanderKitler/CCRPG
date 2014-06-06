/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject;

import com.base.engine.GameObject;
import com.base.engine.Main;
import com.base.game.Delay;
import com.base.game.Time;
import com.base.game.Util;
import java.util.ArrayList;
import org.lwjgl.input.Keyboard;

/**
 *
 * @author sebastian
 */
public class Player extends StatObject
{
    public static final float SIZE = 32;
    
    public static final int FORWARD = 0;
    public static final int BACKWARD = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    
    private Inventory inventory;
    
    private int facingDirection;
    private int attackRange;
    private Delay attackDelay;
    private int attackDamage;
  
    
    public Player(float x, float y)
    {
        init(x,y,0.1f,1f,0.25f,SIZE,SIZE,PLAYER_ID);
        stats = new Stats(0, true);
        inventory = new Inventory(20);
        attackDelay = new Delay(100);
        attackRange = 48;
        attackDamage = 1;
        facingDirection = 0;
        attackDelay.end();
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
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE) && attackDelay.over())
            attack();
    }
    
    public void attack()
    {
        
        
        //Find objects in attack range
        ArrayList<GameObject> objects = new ArrayList<GameObject>();
        
 
        if(facingDirection == FORWARD)
            objects = Main.rectangleCollide(x, y, x + SIZE, y + attackRange);
        else if(facingDirection == BACKWARD)
            objects = Main.rectangleCollide(x, y, x + SIZE, y - attackRange);
        else if(facingDirection == LEFT)
            objects = Main.rectangleCollide(x, y, x - attackRange, y + SIZE);
        else if(facingDirection == RIGHT)
            objects = Main.rectangleCollide(x, y, x + attackRange, y + SIZE);
        
        //Find which objects are enemies
        ArrayList<Enemy> enemies = new ArrayList<Enemy>();
        
        for(GameObject go : objects)
        {
            if(go.getType() == ENEMY_ID)
                enemies.add((Enemy)go);
        }
        // Find Closest Enemy,if Multiple ones Exist
        if (enemies.size() > 0)
        {
            Enemy target = enemies.get(0);
                
            if(enemies.size() > 1)
            {
                for(Enemy e : enemies)
                {
                    if(Util.dist(x, y, e.getX(), e.getY()) < Util.dist(x, y, target.getX(), target.getY()))
                        target = e;
                }
            }

            
            //Attack the Enemy
            target.damage(attackDamage);
            System.out.println(" : " + target.getCurrentHealth() + "/" + target.getMaxHealth());
        }
        else
            System.out.println(" : No target!");
        
        attackDelay.start();
    }
    
    private void move(float magX, float magY)
    {
        if(magX == 0 && magY == 1)
            facingDirection = FORWARD;
        if(magX == 0 && magY == -1)
            facingDirection = BACKWARD;
        if(magX == -1 && magY == 0)
            facingDirection = LEFT;
        if(magX == 1 && magY == 0)
            facingDirection = RIGHT;
        
        x += getSpeed() * magX * Time.getDelta();
        y += getSpeed() * magY * Time.getDelta();
    }
    
        public void addItem(Item item)
    {
        inventory.add(item);
    }
        
    public void addXp(float amt)
    {
        stats.addXp(amt);
    }

}
