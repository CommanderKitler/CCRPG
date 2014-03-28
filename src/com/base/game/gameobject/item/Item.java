/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject.item;

import com.base.engine.GameObject;
import com.base.engine.Sprite;

/**
 *
 * @author sebastian
 */
public class Item extends GameObject
{
    protected String name;
    
    public void pickUp()
    {
        
    }

    
    protected void init( float x, float y, float r, float g, float b, float sx, float sy, String name)
    {
        this.x = x;
        this.y = y;
        this.type = type;
        this.spr = new Sprite(r,g,b,sx,sy);
        this.name = name;
    }
    
}
