package com.base.engine;

/**
 * 
 * @author Sebastian
 */

import com.base.engine.Sprite;
import static org.lwjgl.opengl.GL11.*;

public abstract class GameObject
{
    protected float x;
    protected float y;
    protected int type;
    protected Sprite spr;
    
    public void update()
    {
        
    }
    
    public void render()
    {
        glPushMatrix();
        {
            glTranslatef(x,y,0);
            spr.render();
        }
        glPopMatrix();
    }
    
    public float getX()
    {
        return x;
    }
    
    public float getY()
    {
        return y;
    }
    
    public float getSX()
    {
        return spr.getSX();
    }
    public float getSY()
    {
        return spr.getSY();
    }
    
    public int getType()
    {
        return type;
    }
    protected void init( float x, float y, float r, float g, float b, float sx, float sy, int type)
    {
        this.x = x;
        this.y = y;
        this.type = type;
        this.spr = new Sprite(r,g,b,sx,sy);
    }
    
}