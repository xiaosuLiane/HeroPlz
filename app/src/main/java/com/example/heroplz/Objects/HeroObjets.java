package com.example.heroplz.Objects;

import android.content.Context;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.heroplz.R;
import com.example.heroplz.Timer;

import java.util.List;

public class HeroObjets extends EntityObjets {
    Timer timer_a0,timer_f0;
    boolean _a0;
    public HeroObjets(Context context,int[] tick,int health) {
        super(context,tick,health);
        timer_a0 = new Timer();
        timer_f0 = new Timer();
        _a0 = false;
    }
    public void update_a0(){
        if(timer_a0.isDelay(300)){
            _a0 = !_a0;
            setBackgroundResource(_a0 ? R.drawable.hero1 : R.drawable.hero2);
            timer_a0.reset();
        }
    }
    public void AddFireToGame(ConstraintLayout GamePanel, List<FireObjects> FireQueue, Context activity){
        if(timer_f0.isDelay(300)){//old value:100
            FireObjects fire = new FireObjects(activity,getX()+(getWidth()/2),getY(),
                    true);
            fire.setBackground(getResources().getDrawable(R.drawable.bullet1));
            GamePanel.addView(fire);
            FireQueue.add(fire);
            timer_f0.reset();
        }
    }
}