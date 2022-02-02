package com.example.heroplz.Objects;

import android.content.Context;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.heroplz.R;
import com.example.heroplz.Timer;

import java.util.List;
import java.util.Random;

public class EnemyObjects extends EntityObjets {
    enum Direction{Up,Down,Left,Right}
    Timer _m0,_f0;
    Random random = new Random();

    public EnemyObjects(Context context,float RandomX,int tick[],int health) {
        super(context,tick,health);
        setX((random.nextFloat() * (RandomX-200))+57);
        setY(-10);
        _m0 = new Timer();
        _f0 = new Timer();
    }
    public void MoveTo(Direction direction){
        switch(direction){
            case Up:setY(getY()-16);break; case Down:setY(getY()+16);break;
            case Left:setX(getX()-16);break; case Right:setX(getX()+16);break;
        }
    }
    public void Hey$YouNeedMove(){
        if(_m0.isDelay(50)){
            MoveTo(Direction.Down);
            _m0.reset();
        }
    }
    public void AddFireToGame(ConstraintLayout GamePanel, List<FireObjects> FireQueue, Context activity){
        if(_f0.isDelay(500)){
            FireObjects fire = new FireObjects(activity,getX()+(getWidth()/2),getY()+getHeight(),
                    false);
            fire.setBackground(getResources().getDrawable(R.drawable.bullet2));
            GamePanel.addView(fire);
            FireQueue.add(fire);
            _f0.reset();
        }
    }
}