package com.example.heroplz;

public class Timer {
    long currentTime;
    public Timer(){reset();}
    public void reset(){this.currentTime = System.currentTimeMillis();}
    public boolean isDelay(long delay){return System.currentTimeMillis() - this.currentTime >= delay;}
}