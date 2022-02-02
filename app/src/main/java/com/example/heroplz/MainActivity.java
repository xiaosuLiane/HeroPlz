package com.example.heroplz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.heroplz.Objects.EnemyObjects;
import com.example.heroplz.Objects.FireObjects;
import com.example.heroplz.Objects.HeroObjets;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    ConstraintLayout GamePanel;
    List<FireObjects> FireQueue;
    List<EnemyObjects> EnemyQueue;
    HeroObjets HeroObject;
    Timer _s0;
    float GameWidth,GameHeight;

    public void init(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        GameWidth = dm.widthPixels;
        GameHeight = dm.heightPixels;
        System.out.println("屏幕大小-> Width:"+GameWidth+" Height:"+GameHeight);
        _s0 = new Timer();
        HeroObject = new HeroObjets(getApplicationContext(),new int[]{R.drawable.hero_blowup_n1,
        R.drawable.hero_blowup_n2,R.drawable.hero_blowup_n3,R.drawable.hero_blowup_n4},3);
        HeroObject.setX(GameWidth/2);
        HeroObject.setY(GameHeight/2);
        HeroObject.setBackground(getResources().getDrawable(R.drawable.hero1));
        FireQueue = new ArrayList<>();
        EnemyQueue = new ArrayList<>();
        GamePanel.addView(HeroObject);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GamePanel = findViewById(R.id.GamePanel);
        GamePanel.setOnTouchListener(this);
        init();
        handler.sendEmptyMessage(0);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch(motionEvent.getAction()){
            case MotionEvent.ACTION_MOVE:
                HeroObject.setX(motionEvent.getX()-(HeroObject.getWidth()/2));
                HeroObject.setY(motionEvent.getY());
                System.out.println(motionEvent.getX()+" "+motionEvent.getY());break;
        }
        return true;
    }

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message message) {
            if(_s0.isDelay(1800)){
                EnemyObjects enemyObject = new EnemyObjects(getApplicationContext(),GameWidth,
                        new int[]{R.drawable.enemy1_down1,R.drawable.enemy1_down2,R.drawable.enemy1_down3,
                        R.drawable.enemy1_down4},3);
                enemyObject.setBackground(getDrawable(R.drawable.enemy1));
                GamePanel.addView(enemyObject);
                EnemyQueue.add(enemyObject);
                _s0.reset();
            }
            if(HeroObject.Hey$DoYouDead()){
                System.exit(0);
            }
            HeroObject.AddFireToGame(GamePanel,FireQueue,getApplicationContext());
            int length = FireQueue.size(),length0,i,j;
            for(i = 0;i < length;i++){
                if(FireQueue.get(i).getY() < 0 || FireQueue.get(i).getY() > GameHeight){
                    length--;GamePanel.removeView(FireQueue.get(i));FireQueue.remove(i);continue;
                }else{
                    FireQueue.get(i).setY(FireQueue.get(i).getFireFormPlayer() ? FireQueue.get(i).getY()-20
                            : FireQueue.get(i).getY()+20);
                    if(FireQueue.get(i).getFireFormPlayer()){
                        length0 = EnemyQueue.size();
                        for(j = 0;j < length0;j++)
                            if(!EnemyQueue.get(j).getDead() && uTILS.Collide(FireQueue.get(i).getX(),
                                    FireQueue.get(i).getY(),
                                    EnemyQueue.get(j).getX(),EnemyQueue.get(j).getY(),
                                    EnemyQueue.get(j).getWidth(),EnemyQueue.get(j).getHeight())){
                                if(EnemyQueue.get(j).Give$HarmIfDead(1))
                                    EnemyQueue.get(j).setDead(true);
                                GamePanel.removeView(FireQueue.get(i));FireQueue.remove(i);
                                length--;break;
                            }
                    }else{
                        if(!HeroObject.getDead() && uTILS.Collide(FireQueue.get(i).getX(),
                                FireQueue.get(i).getY(), HeroObject.getX(),HeroObject.getY(),
                                HeroObject.getWidth(), HeroObject.getHeight())){
                            GamePanel.removeView(FireQueue.get(i));FireQueue.remove(i);length--;
                            if(!HeroObject.getDead()){
                                if(HeroObject.Give$HarmIfDead(1)){
                                    HeroObject.setDead(true);
                                }
                            }
                        }
                    }
                }
            }
            length = EnemyQueue.size();
            for(i = 0;i < length;i++){
                if(EnemyQueue.get(i).getY() > GameHeight){
                    length--;GamePanel.removeView(EnemyQueue.get(i));EnemyQueue.remove(i);continue;
                }
                if(EnemyQueue.get(i).Hey$DoYouDead()){
                    GamePanel.removeView(EnemyQueue.get(i));EnemyQueue.remove(i);length--;continue;
                }
                if(EnemyQueue.get(i).getX() >= HeroObject.getX() &&
                        EnemyQueue.get(i).getX() <= HeroObject.getX()+HeroObject.getWidth()){
                    EnemyQueue.get(i).AddFireToGame(GamePanel,FireQueue,getApplicationContext());
                }
                EnemyQueue.get(i).Hey$YouNeedMove();
            }
            System.out.println("线程目前处于存活状态.\tf:"+FireQueue.size()+"\te:"+EnemyQueue.size());
            if(!HeroObject.getDead())
                HeroObject.update_a0();
            handler.sendEmptyMessageDelayed(0,20); return true;
        }
    });
}