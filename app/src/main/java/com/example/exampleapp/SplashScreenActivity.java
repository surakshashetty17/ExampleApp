package com.example.exampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        try{
//            VideoView videoView = new VideoView(this);
            VideoView videoView = (VideoView)findViewById(R.id.videoView);
//            setContentView(R.layout.activity_splash);
            Uri path = Uri.parse( "android.resource://"+getPackageName()+"/"+ +R.raw.nano_cart);
            videoView.setVideoURI(path);

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    jump();
                }


            });
            videoView.start();
        }catch (Exception e){
            jump();
        }
    }
    private void jump() {

        if(isFinishing())
            return;
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
