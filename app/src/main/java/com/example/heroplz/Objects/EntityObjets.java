package com.example.heroplz.Objects;

import android.content.Context;
import android.widget.TextView;

import com.example.heroplz.Timer;

public class EntityObjets extends TextView {
    boolean isDead;
    int _0a,_0al;
    int _ai[];
    int health;
    Timer _a0;
    public EntityObjets(Context context,int tick[],int health) {
        super(context);
        _a0 = new Timer();
        isDead = false;
        _0a = 0;
        _ai = tick;
        _0al = _ai.length;
        this.health = health;
    }
    public void setDead(boolean isDead){this.isDead = isDead;}
    public boolean getDead(){return this.isDead;}
    public void setHealth(int health){this.health = health;}
    public int getHealth(){return this.health;}
    public boolean Hey$DoYouDead(){
        if(getDead()){
            if(_a0.isDelay(100)){//old value:300
                if(_0a >= _0al){
                    return true;
                }else
                    setBackgroundResource(_ai[_0a++]);
                _a0.reset();
            }
        }
        return false;
    }
    public boolean Give$HarmIfDead(int harm){
        this.health -= harm;
        if(this.health <= 0)
            return true;
        return false;
    }
}