package com.example.guest.movieapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.editText) EditText mMovieTitle;
    @Bind(R.id.button) Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mMovieTitle.getText().equals("")) {
                    Toast.makeText(MainActivity.this, "Enter movie title", Toast.LENGTH_SHORT);
                } else {
                    Intent myIntent = new Intent(MainActivity.this, ResultsActivity.class);
                    myIntent.putExtra("title", mMovieTitle.getText().toString());
                    startActivity(myIntent);
                }
            }
        });
    }
}
