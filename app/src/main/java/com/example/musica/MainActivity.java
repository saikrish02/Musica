package com.example.musica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    MediaPlayer musicPlayer ;
    TextView textView ;
    Button play, pause, stop;
    AudioManager audioManager;
    ImageView play1, pause1,stop1;
    Spinner dropdown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // play, pause, stop buttons
        play = findViewById(R.id.play);
        pause = findViewById(R.id.pause);
        stop = findViewById(R.id.stop);
        dropdown = findViewById(R.id.Dropdown);
        play1 = (ImageView) findViewById(R.id.imageView2);
        pause1 = (ImageView) findViewById(R.id.imageView3);
        stop1 = (ImageView)findViewById(R.id.imageView);
        ArrayAdapter<CharSequence>arrayAdapter = ArrayAdapter.createFromResource(this, R.array.Songs, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(arrayAdapter);
        musicPlayer = MediaPlayer.create(this, R.raw.music1);
        dropdown.setOnItemSelectedListener( this);
        // attaching the music1 file to the activity using the music1 player object.


        play1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Music is being Played", Toast.LENGTH_SHORT).show();
                // playing the music1
                musicPlayer.start();
            }
        });
        pause1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Music has been Paused", Toast.LENGTH_SHORT).show();
                // pausing the music1
                musicPlayer.pause();
            }
        });
        stop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Music stopped. Click Start to Restart again. ", Toast.LENGTH_SHORT).show();
                // stopping the music1
                musicPlayer.stop();
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Music is being Played", Toast.LENGTH_SHORT).show();
                // playing the music1
                musicPlayer.start();
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Music has been Paused", Toast.LENGTH_SHORT).show();
                // pausing the music1
                musicPlayer.pause();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Music stopped. Click Start to Restart again. ", Toast.LENGTH_SHORT).show();
                // stopping the music1
                musicPlayer.stop();
            }
        });
         // audio manager is used to avail the info and services from the system and get the stream volume
        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

       int maxVol =  audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
       int curVol =  audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        SeekBar seekBar = findViewById(R.id.seekBar);
        // setting the max value for the seek bar based on the system's service
        seekBar.setMax(maxVol);
        // initializing the seekbar to zero
        seekBar.setProgress(curVol);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // to set the progress on changing the status of the seek bar.
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final SeekBar progress = findViewById(R.id.seekBar3);
        progress.setMax(musicPlayer.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                progress.setProgress(musicPlayer.getCurrentPosition());
            }
        }, 0, 10000);
        progress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //if(fromUser)
                musicPlayer.seekTo(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        musicPlayer.release();
        if(position == 0)
        musicPlayer = MediaPlayer.create(this, R.raw.music1);
        else if(position == 1)
            musicPlayer = MediaPlayer.create(this, R.raw.music2);
        else
            musicPlayer = MediaPlayer.create(this, R.raw.music3);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}