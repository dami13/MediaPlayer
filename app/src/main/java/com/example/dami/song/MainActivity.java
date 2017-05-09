package com.example.dami.song;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.R.attr.button;

public class MainActivity extends AppCompatActivity {
    float volume=1;
    final int SKIP_MS=5000; //5s
    final float VOLUME_SCALLE=(float)0.05;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.test_song);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            public void onCompletion(MediaPlayer mP){
                Toast.makeText(MainActivity.this, "I\'m done!", Toast.LENGTH_SHORT).show();
            }
        });
        //setting listener for START && STOP buttons
        Button startButton=(Button) findViewById(R.id.start_button_view);
        startButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mediaPlayer.start();
            }
        });

        Button stopButton=(Button) findViewById(R.id.stop_button_view);
        stopButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                mediaPlayer.pause();
            }
        });

        Button vollumeUpButton=(Button) findViewById(R.id.volume_up_button_view);
        vollumeUpButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                volume+=VOLUME_SCALLE;

                if(volume>1)
                    volume=1;
                mediaPlayer.setVolume(volume, volume);
            }
        });

        Button vollumeDownButton=(Button) findViewById(R.id.volume_down_button_view);
        vollumeDownButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                volume-=VOLUME_SCALLE;

                if(volume<0)
                    volume=0;
                mediaPlayer.setVolume(volume, volume);
            }
        });

        Button skipButton=(Button) findViewById(R.id.skip_5s_button_view);
        skipButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                int currentDuration=mediaPlayer.getCurrentPosition();

                if(currentDuration>mediaPlayer.getDuration()+SKIP_MS)
                    mediaPlayer.seekTo(mediaPlayer.getDuration());
                else
                    mediaPlayer.seekTo(currentDuration+SKIP_MS);
            }
        });
    }
}
