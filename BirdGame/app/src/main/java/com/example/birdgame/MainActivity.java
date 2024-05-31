package com.example.birdgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView car,mon1,mon2,mon3,coin,volume;
    Button startbtn;
    private Animation animation;
    MediaPlayer mediaPlayer;
    boolean status=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        car=findViewById(R.id.car);
        mon1=findViewById(R.id.mon1);
        mon2=findViewById(R.id.spikemon);
        mon3=findViewById(R.id.pinkmon);
        coin=findViewById(R.id.coin);
        volume=findViewById(R.id.volume);
        startbtn=findViewById(R.id.btnStart);

        animation= AnimationUtils.loadAnimation(MainActivity.this,R.anim.scale_animation);
        animation.setRepeatCount(1000);
       // animation.setRepeatMode(Animation.REVERSE);
        animation.setRepeatMode(Animation.INFINITE);
        animation.setDuration(1000);

        car.setAnimation(animation);
        mon1.setAnimation(animation);
        mon2.setAnimation(animation);
        mon3.setAnimation(animation);
        coin.setAnimation(animation);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.background_sound);
        mediaPlayer.start();
        mediaPlayer.setLooping(true);

        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!status){
                    mediaPlayer.setVolume(0,0);
                    volume.setImageResource(R.drawable.baseline_volume_off_24);
                    status=true;
                }
                else{
                    mediaPlayer.setVolume(1,1);
                    volume.setImageResource(R.drawable.baseline_volume_up_24);
                    status=false;
                }
            }
        });

        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.reset();
                Intent i=new Intent(MainActivity.this,GameActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}