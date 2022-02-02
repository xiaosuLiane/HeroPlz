package com.example.heroplz;

public class uTILS {
    public static boolean Collide(float x,float y,float x1,float y1,int w1,int h1){
        if((x >= x1 && x <= x1+w1) && (y >= y1 && y <= y1 + h1))
            return true;
        return false;
    }
}