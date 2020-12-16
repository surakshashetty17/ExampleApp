package com.example.exampleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class LoginActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 10000;
    TextView text;
    Button button,button1,buttonscanner;
    public int counter;

    private CountDownTimer mcountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final CountDownTimer remainingTimeCounter = new CountDownTimer(10000, 1000) {
            public void onTick(long millisUntilFinished) {

                mTimeLeftInMillis = millisUntilFinished;
//                        text.setText(String.valueOf(counter));
//                        counter++;
                int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
                int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
                String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);

                text.setText(timeLeftFormatted);
                button.setVisibility(View.INVISIBLE);
            }

            public void onFinish() {
                Log.d("Counter", "Finished....");
                Toast.makeText(LoginActivity.this, "Time Out..Start Again!!", Toast.LENGTH_LONG).show();
                button.setVisibility(View.VISIBLE);

            }
        }.start();

        text=(TextView)findViewById(R.id.text11);
        button1=(Button)findViewById(R.id.button111);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,CameraPhotoActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        });

        buttonscanner=(Button)findViewById(R.id.buttonbarcode);
        buttonscanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(LoginActivity.this,ScannerActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        });

        button=(Button)findViewById(R.id.button11);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remainingTimeCounter.cancel();
                remainingTimeCounter.start();

//                startTimer();
//                new CountDownTimer(mTimeLeftInMillis, 1000){
////                    mcountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
//                    public void onTick(long millisUntilFinished){
//                        mTimeLeftInMillis = millisUntilFinished;
////                        text.setText(String.valueOf(counter));
////                        counter++;
//                        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
//                        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
//                        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
//
//                        text.setText(timeLeftFormatted);
//                    }
//                    public  void onFinish(){
////                        text.setText("FINISH!!");
//                        Toast.makeText(LoginActivity.this, "Finish", Toast.LENGTH_LONG).show();
//                        button.setVisibility(View.INVISIBLE);
//                    }
//                }.start();

            }
        });
    }
//    private void startTimer()
//    {
//        mcountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                mTimeLeftInMillis = millisUntilFinished;
//                updateCountDownText();
//            }
//
//            @Override
//            public void onFinish() {
//
////                text.setText("00:00:00");
//                Toast.makeText(LoginActivity.this, "Finish", Toast.LENGTH_LONG).show();
//                cancel();
//            }
//        }.start();
//
//        mTimerRunning = true;
//    }

//    private void updateCountDownText()
//    {
//        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
//        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
//
//        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
//
//        text.setText(timeLeftFormatted);
//
//
//    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(this, WelcomeActivity.class);
        i.putExtra("exit", true);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        super.onBackPressed();
    }
}
