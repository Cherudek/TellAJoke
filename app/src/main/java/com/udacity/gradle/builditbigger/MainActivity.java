package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.example.androiddisplayjokeslibrary.DisplayJokeActivity;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask.AsyncTaskCallback;


public class MainActivity extends AppCompatActivity implements
    AsyncTaskCallback {

  public Context mContext;
  public AsyncTaskCallback asyncTaskCallback = this;

  private String MAIN_ACTIVITY_LOG_TAG = MainActivity.class.getName();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mContext = getApplicationContext();

  }

  @Override
  public void callBack(String joke) {
    Intent intent = new Intent(this, DisplayJokeActivity.class);
    intent.putExtra(DisplayJokeActivity.JOKE_INTENT_TAG, joke);
    this.startActivity(intent);
    asyncTaskCallback.callBack(joke);

    Log.i(MAIN_ACTIVITY_LOG_TAG, "TEST *** The Joke is: " + joke);
  }
}
