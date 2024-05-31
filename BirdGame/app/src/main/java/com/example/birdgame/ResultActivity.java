package com.example.birdgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class ResultActivity extends AppCompatActivity {
    TextView textViewResultInfo,textViewScore,textViewHighestScore;
    Button buttonAgain;
    int score;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewHighestScore=findViewById(R.id.textViewResultHIghestScore);
        textViewScore=findViewById(R.id.textViewResultScore);
        textViewResultInfo=findViewById(R.id.textViewResultINfo3);

        buttonAgain=findViewById(R.id.buttonAgain);

        score=getIntent().getIntExtra("score",0);

        textViewScore.setText("Your score:"+score);

        sharedPreferences=this.getSharedPreferences("Score", Context.MODE_PRIVATE);
        int highestscore=sharedPreferences.getInt("highestScore",0);

        if(score>=200){
            textViewResultInfo.setText("You won the Game");
            textViewHighestScore.setText("Highest Score:"+score);
            sharedPreferences.edit().putInt("highestScore",score).apply();
        } else if (score>=highestscore) {
            textViewResultInfo.setText("Sorry,you lost the game");
            textViewHighestScore.setText("Highest Score:"+score);
            sharedPreferences.edit().putInt("highestScore",score).apply();
            
        }
        else {
            textViewScore.setText("Sorry you lost the game");
            textViewHighestScore.setText("Highest Score:"+highestscore);
        }

        buttonAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this,MainActivity.class));
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder=new AlertDialog.Builder(ResultActivity.this);
        builder.setTitle("Run bitches");
        builder.setMessage("Are you sure you want to quit?");
        builder.setCancelable(false);
        builder.setNegativeButton("Quit Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        });
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.create().show();


    }
}