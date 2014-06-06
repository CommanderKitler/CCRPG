/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.game.gameobject;

import com.base.engine.GameObject;
import static com.base.engine.GameObject.PLAYER_ID;
import com.base.engine.Main;
import com.base.engine.Sprite;
import com.base.game.Delay;
import com.base.game.Time;
import com.base.game.Util;
import java.util.ArrayList;
/**
 *
 * @author sebastian
 */
public class Enemy extends StatObject
{
   
    private StatObject target;
    private float attackRange;
    protected int attackDamage;
    private Delay attackDelay;
    private float sightRange;
    
    public static final float DAMPING = 0.5f;
    
    
    public Enemy(int level)
    {
        stats = new Stats(level, false);
        target = null;
        attackDelay = new Delay(500);       
        attackRange = 48f;
        attackDamage = 1;
        attackDelay.end();
        sightRange = 128;
   
        
    }
    
    @Override
    public void update()
    {
        if(target == null)
            Look();
        else
        {
           
        
        if(Util.LineOfSight(this, target) && Util.LineOfSight(this, target) && Util.dist(x, y, getTarget().getX(), getTarget().getY()) <= attackRange)
        {
            if(attackDelay.over())
            Attack();
        }
            else
                Chase();
                
        
        }
        if(stats.getCurrentHealth() <= 0)
            Death();
        
    }
    
    protected void Attack()
    {
        getTarget().damage(attackDamage);
        System.out.println("We're hit! " + getTarget().getCurrentHealth() + "/" + getTarget().getMaxHealth());
        restartAttackDelay();
    }
    
        protected void Death()
    {
        remove();
    }
        
    protected void Look()
    {
                ArrayList<GameObject> objects = Main.sphereCollide(x, y, sightRange);
        
        for(GameObject go : objects)
            if(go.getType() == PLAYER_ID)
                setTarget((StatObject)go);
    }
    
    protected void Chase()
    {
                float speedX = (getTarget().getX() - x);
        float speedY = (getTarget().getY() - y);
        
        float maxSpeed = getStats().getSpeed() * DAMPING;
        
        if(speedX > maxSpeed)
            speedX = maxSpeed;
        if(speedX < -maxSpeed)
            speedX = -maxSpeed;
        
        if(speedY > maxSpeed)
            speedY = maxSpeed;
        if(speedY < -maxSpeed)
            speedY = -maxSpeed;
        
        x = x + speedX * Time.getDelta();
        y = y + speedY * Time.getDelta();
    }
    

    public void setTarget(StatObject go)
    {
        target = go;
    }
    public StatObject getTarget()
    {
        return target;
    }
    
    public Stats getStats()
    {
        return stats;
    }
    
    public int getAttackDamage(int amt)
    {
        return attackDamage;
    }
    
    public void setAttackRange(int range)
    {
        attackRange = range;
    }
    
    public void setAttackDelay(int time)
    {
        attackDelay = new Delay(time);
        attackDelay.end();
   
    }
    
    public void setAttackDamage(int amt)
    {
        attackDamage = amt;
    }
 
    public void restartAttackDelay()
    {
        attackDelay.start();
    }
    
    public void setSightRange(float dist)
    {
        sightRange = dist;
    }
    
    @Override
      protected void init( float x, float y, float r, float g, float b, float sx, float sy, int type)
    {
        this.x = x;
        this.y = y;
        this.type = ENEMY_ID;
        this.spr = new Sprite(r,g,b,sx,sy);
    }
}
