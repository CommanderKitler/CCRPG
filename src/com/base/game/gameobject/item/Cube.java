
package com.base.game.gameobject.item;

import com.base.game.gameobject.Player;

/**
 *
 * @author sebastian
 */
public class Cube extends Item
{
    public static final float SIZE = 32;
    
   

    
    public Cube(float x, float y, Player play)
    {
        super(play);
       init(x,y,1.0f,0.5f,0,SIZE,SIZE,"the Cube");
       
       
    }
    

    
}
