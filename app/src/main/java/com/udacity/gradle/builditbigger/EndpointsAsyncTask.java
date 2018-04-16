package com.udacity.gradle.builditbigger;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.util.Pair;
import android.util.Log;
import com.example.androiddisplayjokeslibrary.DisplayJokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import java.io.IOException;

class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> implements
    MainActivity.asyncTaskCallback {

  public static final String LOG_TAG = EndpointsAsyncTask.class.getCanonicalName();

  private static MyApi myApiService = null;

  private Context mContext;
  private String mJoke;

  @Override
  protected String doInBackground(Pair<Context, String>... params) {

    if (myApiService == null) {  // Only do this once
      MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
          new AndroidJsonFactory(), null)
          // options for running against local devappserver
          // - 10.0.2.2 is localhost's IP address in Android emulator
          // - turn off compression when running against local devappserver
          .setRootUrl("http://10.0.2.2:8080/_ah/api/")
          .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
            @Override
            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) {
              abstractGoogleClientRequest.setDisableGZipContent(true);
            }
          });
      // end options for devappserver

      myApiService = builder.build();
    }

    try {
      String joke = String.valueOf(myApiService.sayHi().execute().getData());
      Log.i(TAG, " TEST**** The Joke Retrieved is: " + joke);

      return joke;
    } catch (IOException e) {
      return e.getMessage();
    }
  }


  @Override
  protected void onPostExecute(final String mResult) {

    mJoke = mResult;

    Log.i(LOG_TAG, " TEST **** The Joke Retrieved is: " + mResult);


  }


  @Override
  public void callBackCall(Context context) {
    mContext = context;

    Intent intent = new Intent(mContext, DisplayJokeActivity.class);
    intent.putExtra(DisplayJokeActivity.JOKE_INTENT_TAG, mJoke);
    mContext.startActivity(intent);

  }
}
