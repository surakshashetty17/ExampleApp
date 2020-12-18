package com.example.exampleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class SharingActivity extends AppCompatActivity {

    RatingBar ratingbar;
    Button buttonrating;
    TextView textrating;
    EditText editreview;
    ImageView imageshare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing);

        ratingbar = findViewById(R.id.ratingBar);
        editreview = findViewById(R.id.editreview);
        buttonrating = findViewById(R.id.ratingbutton);
        textrating = findViewById(R.id.textrating);
        imageshare = findViewById(R.id.share);
        buttonrating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                String review = String.valueOf(editreview);
                String review = editreview.getText().toString();
                String rating=String.valueOf(ratingbar.getRating());
                Toast.makeText(getApplicationContext(), rating, Toast.LENGTH_LONG).show();
                textrating.setText("Rating: "+rating+"\n"+"Review: "+review);
            }
        });
        imageshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String sharebody = ("Rating and Review: "+String.valueOf(ratingbar.getRating())+" and "+(editreview.getText().toString()));
                String sharesubject = "Your Subject here";

                intent.putExtra(Intent.EXTRA_TEXT,sharebody);
                intent.putExtra(Intent.EXTRA_SUBJECT,sharesubject);

                startActivity(Intent.createChooser(intent,"shareusing"));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String sharebody = ("Rating and Review: "+String.valueOf(ratingbar.getRating())+" and "+(editreview.getText().toString()));
                String sharesubject = "Your Subject here";

                intent.putExtra(Intent.EXTRA_TEXT,sharebody);
                intent.putExtra(Intent.EXTRA_SUBJECT,sharesubject);

                startActivity(Intent.createChooser(intent,"shareusing"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
