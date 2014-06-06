/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.base.game.gameobject.enemys;

import com.base.game.gameobject.Enemy;

/**
 *
 * @author sebastian
 */
public class PinkGiant extends Enemy
{
    public static final int HEIGHT = 64;
    public static final int WIDTH = 32;
    
    public PinkGiant(float x, float y, int level)
    {
        super(level);
        this.init(x, y, 1.0f, 0.2f, 1.0f, WIDTH, HEIGHT, 0);
        setAttackDelay(200);
    }
}
