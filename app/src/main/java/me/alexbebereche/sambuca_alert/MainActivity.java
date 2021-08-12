package me.alexbebereche.sambuca_alert;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnStart;
    Button btnStop;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        getSupportActionBar().hide();

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnStop.setVisibility(View.INVISIBLE);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play(v);
                btnStart.setVisibility(View.INVISIBLE);
                btnStop.setVisibility(View.VISIBLE);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlayer();
                btnStart.setVisibility(View.VISIBLE);
                btnStop.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void play(View v){
        if(player == null){
            player = MediaPlayer.create(this, R.raw.samb); // no need to put an id, just put its name
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                    btnStart.setVisibility(View.VISIBLE);
                    btnStop.setVisibility(View.INVISIBLE);
                }
            });
        }

        player.start();
    }

    public void stopPlayer(){
        if(player != null){
            player.release();
            player = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();

    }
}