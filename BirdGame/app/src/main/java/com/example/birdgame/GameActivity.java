package com.example.birdgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    ImageView car,mon1,mon2,mon3,coin1,coin2,life1,life2,life3;
    TextView textViewScore,textViewStartInfo;
    ConstraintLayout constraintLayout;
    boolean touchControl=false,beginControl=false;
    Runnable runnable,runnable2;
    Handler handler,handler2;
    //positions
    int carX,carY,mon1X,mon1Y,mon2X,mon2Y,mon3X,mon3Y,coin1X,coin1Y,coin2X,coin2Y;
    //dimensions of screen
    int screenHeight,screenWidth;
    //remaining lives
    int lives=3;
    //points
    int score=0;
    Random random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        car=findViewById(R.id.imageViewCar);
        mon1=findViewById(R.id.imageViewmon1);
        mon2=findViewById(R.id.imageViewmon2);
        mon3=findViewById(R.id.imageViewmon3);
        coin1=findViewById(R.id.imageViewCoin1);
        coin2=findViewById(R.id.imageViewCoin2);
        life1=findViewById(R.id.imageViewLife1);
        life2=findViewById(R.id.imageViewLife2);
        life3=findViewById(R.id.imageViewLife3);
        textViewScore=findViewById(R.id.textViewScore);
        textViewStartInfo=findViewById(R.id.textViewStartInfo);
        constraintLayout=findViewById(R.id.constraintLayout);
        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
           //        @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                textViewStartInfo.setVisibility(View.INVISIBLE);

                if(!beginControl){

                    screenWidth=(int) constraintLayout.getWidth();
                    screenHeight=(int) constraintLayout.getHeight();

                    carY=(int) car.getY();
                    carX=(int) car.getX();
                    beginControl=true;
                    handler=new Handler();
                    runnable=new Runnable() {
                        @Override
                        public void run() {
                            movetoCar();
                            enemyControl();
                            collisionControl();
                        }
                    };
                    handler.post(runnable);
                    
                }
                else{
                    if(event.getAction()==MotionEvent.ACTION_DOWN){
                        touchControl=true;
                    }
                    if(event.getAction()==MotionEvent.ACTION_UP){

                        touchControl=false;
                    }
                }

                return true;
            }
        });

    }

    public void movetoCar(){
        Log.e("moveTOcar","MoveToCar");
        if(touchControl){
            carY=carY-(screenHeight/70);

        }
        else {
            carY=carY+(screenHeight/50);
        }
//zero is at the left top corner
        if(carY<=screenHeight/2.60){
            carY= (int) (screenHeight/2.60);
        }
        if(carY>=(screenHeight-car.getHeight())){
            carY= (int) (screenHeight-car.getHeight());
        }

        car.setY(carY);
    }

    public void enemyControl(){
    mon1.setVisibility(View.VISIBLE);
    mon2.setVisibility(View.VISIBLE);
    mon3.setVisibility(View.VISIBLE);
    coin1.setVisibility(View.VISIBLE);
    coin2.setVisibility(View.VISIBLE);
    random=new Random();

        mon1X=mon1X-(screenWidth/150);
        if(score>=50 && score<100){
            mon1X=mon1X-(screenWidth/130);
        }
        if(score>100&& score<150){
            mon1X=mon1X-(screenWidth/120);
        }
        if(score>=150){
            int speed=random.nextInt(100)+50;
            mon1X=mon1X-(screenWidth/speed);
        }
    if(mon1X<0) {
        Log.e("mon1x","speed");
        mon1X = screenWidth + 200;
        //mon1Y=new Random (screenHeight).nextInt();
        mon1Y= (int) Math.floor(Math.random()*screenHeight);
        if(mon1Y<=screenHeight/2.20){
            mon1Y= (int) (screenHeight/2.20);
        }
        if(mon1Y>=(screenHeight-car.getHeight())){
            mon1Y= (int) (screenHeight-car.getHeight());
        }
    }

        mon1.setX(mon1X);
        mon1.setY(mon1Y);

        mon2X=mon2X-(screenWidth/130);
        if(score>=50 && score<100){
            mon2X=mon2X-(screenWidth/110);
        }
        if(score>100&& score<150){
            mon2X=mon2X-(screenWidth/100);
        }
        if(score>=150){
            int speed=random.nextInt(100)+50;
            mon2X=mon2X-(screenWidth/speed);
        }
    if(mon2X<0) {
        mon2X = screenWidth + 200;
        //mon1Y=new Random (screenHeight).nextInt();
        mon2Y= (int) Math.floor(Math.random()*screenHeight);
        if(mon2Y<=screenHeight/2.20){
            mon2Y= (int) (screenHeight/2.20);
        }
        if(mon2Y>=(screenHeight-car.getHeight())){
            mon2Y= (int) (screenHeight-car.getHeight());
        }
    }


        mon2.setX(mon2X);
        mon2.setY(mon2Y);

        mon3X=mon3X-(screenWidth/130);
        if(score>=50 && score<100){
            mon3X=mon3X-(screenWidth/150);
        }
        if(score>100&& score<150){
            mon3X=mon3X-(screenWidth/110);
        }
        if(score>=150){
            int speed=random.nextInt(100)+50;
            mon3X=mon3X-(screenWidth/speed);
        }
    if(mon3X<0) {
        mon3X = screenWidth + 200;
        //mon1Y=new Random (screenHeight).nextInt();
        mon3Y= (int) Math.floor(Math.random()*screenHeight);
        if(mon3Y<=screenHeight/2.20){
            mon3Y= (int) (screenHeight/2.20);
        }
        if(mon3Y>=(screenHeight-car.getHeight())){
            mon3Y= (int) (screenHeight-car.getHeight());
        }
    }

        mon3.setX(mon3X);
        mon3.setY(mon3Y);

    coin1X=coin1X-(screenWidth/120);
    if(coin1X<0) {
        coin1X = screenWidth + 200;
        //coin1Y=new Random ((long) (screenHeight/2.20)).nextInt();
        coin1Y= (int) Math.floor(Math.random()*screenHeight);
        if(coin1Y<=screenHeight/2.20){
            coin1Y= (int) (screenHeight/2.20);
        }
        if(coin1Y>=(screenHeight-car.getHeight())){
            coin1Y= (int) (screenHeight-car.getHeight());
        }
    }

        coin1.setX(coin1X);
        coin1.setY(coin1Y);

    coin2X=coin2X-(screenWidth/100);
    if(coin2X<0) {
        coin2X = screenWidth + 200;
        //mon1Y=new Random (screenHeight).nextInt();
        coin2Y= (int) Math.floor(Math.random()*screenHeight);
        if(coin2Y<=screenHeight/2.60){
            coin2Y= (int) (screenHeight/2.60);
        }
        if(coin2Y>=(screenHeight-car.getHeight())){
            coin2Y= (int) (screenHeight-car.getHeight());
        }
    }

        coin2.setX(coin2X);
        coin2.setY(coin2Y);

    }

    public void collisionControl(){

        Log.e("collision control","collision control");
        int centerMon1X=mon1X+mon1.getWidth()/2;
        int centerMon1Y=mon1Y+mon1.getHeight()/2;

        if(centerMon1X>=carX
        && centerMon1X<=(carX+car.getWidth())
        && centerMon1Y>=carY
        && centerMon1Y<=(carY+car.getHeight())){

            mon1X=screenWidth+200;
            lives--;
        }
        int centerMon2X=mon2X+mon2.getWidth()/2;
        int centerMon2Y=mon2Y+mon2.getHeight()/2;

        if(centerMon2X>=carX
        && centerMon2X<=(carX+car.getWidth())
        && centerMon2Y>=carY
        && centerMon2Y<=(carY+car.getHeight())){

            mon2X=screenWidth+200;
            lives--;
        }
        int centerMon3X=mon3X+mon3.getWidth()/2;
        int centerMon3Y=mon3Y+mon3.getHeight()/2;

        if(centerMon3X>=carX
        && centerMon3X<=(carX+car.getWidth())
        && centerMon3Y>=carY
        && centerMon3Y<=(carY+car.getHeight())){

            mon3X=screenWidth+200;
            lives--;
        } int centercoin1X=coin1X+coin1.getWidth()/2;
        int centercoin1Y=coin1Y+coin1.getHeight()/2;

        if(centercoin1X>=carX
        && centercoin1X<=(carX+car.getWidth())
        && centercoin1Y>=carY
        && centercoin1Y<=(carY+car.getHeight())){

            coin1X=screenWidth+200;
            score=score+1;
            textViewScore.setText(""+score);
        }
        int centercoin2X=coin2X+coin2.getWidth()/2;
        int centercoin2Y=coin2Y+coin2.getHeight()/2;

        if(centercoin2X>=carX
        && centercoin2X<=(carX+car.getWidth())
        && centercoin2Y>=carY
        && centercoin2Y<=(carY+car.getHeight())){

            coin2X=screenWidth+200;
            score=score+1;
            textViewScore.setText(""+score);
        }

        if(lives>0 && score<200){

            if(lives==2){
                life1.setImageResource(R.drawable.grey_life);
            }
            if(lives==1){
                life2.setImageResource(R.drawable.grey_life);
            }
            handler.postDelayed(runnable,20);
        }
        else if (score>=200){
            handler.removeCallbacks(runnable);
            constraintLayout.setEnabled(false);
            textViewStartInfo.setVisibility(View.VISIBLE);
            textViewStartInfo.setText("Congratulations you've won");
            mon1.setVisibility(View.INVISIBLE);
            mon2.setVisibility(View.INVISIBLE);
            mon3.setVisibility(View.INVISIBLE);
            coin1.setVisibility(View.INVISIBLE);
            coin2.setVisibility(View.INVISIBLE);

            handler2=new Handler();
            runnable2=new Runnable() {
                @Override
                public void run() {
                carX=carX+(screenWidth/300);
                car.setX(carX);
                car.setY(screenHeight/2f);
                if(carX<=screenWidth){
                    handler2.postDelayed(runnable2,20);
                }
                else {
                    handler2.removeCallbacks(runnable2);
                    Intent i=new Intent(GameActivity.this,ResultActivity.class);
                    i.putExtra("score",score);
                    startActivity(i);
                    finish();

                }
                }
            };
            handler2.post(runnable2);
        }
        else if(lives==0) {
            handler.removeCallbacks(runnable);
            life3.setImageResource(R.drawable.grey_life);
            Intent i=new Intent(GameActivity.this,ResultActivity.class);
            i.putExtra("score",score);
            startActivity(i);
            finish();
            
        }
    }
}