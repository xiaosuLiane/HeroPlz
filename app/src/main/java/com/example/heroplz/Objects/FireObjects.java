package com.example.heroplz.Objects;

import android.content.Context;
import android.widget.TextView;

public class FireObjects extends TextView {
    boolean fireFormPlayer;
    public FireObjects(Context context,float x,float y,boolean fireFormPlayer) {
        super(context);
        setX(x);
        setY(y);
        setFireFormPlayer(fireFormPlayer);
    }
    public void setFireFormPlayer(boolean fireFormPlayer){this.fireFormPlayer = fireFormPlayer;}
    public boolean getFireFormPlayer(){return this.fireFormPlayer;}
}