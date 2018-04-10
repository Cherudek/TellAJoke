package com.example.androiddisplayjokeslibrary;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class DisplayJokeActivity extends AppCompatActivity {

  public static String JOKE_INTENT_TAG = "Java Joke Intent";
  private String DISPLAY_ACTIVITY_LOG_TAG = DisplayJokeActivity.class.getName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_display_joke_activity);

    TextView tvMyFirstJoke = findViewById(R.id.my_first_joke);

    Intent intent = getIntent();
    String joke = intent.getStringExtra(JOKE_INTENT_TAG);
    Log.i(DISPLAY_ACTIVITY_LOG_TAG, "The Joke Retrieved is: " + joke);
    tvMyFirstJoke.setText(joke);
  }

}
