package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.util.Pair;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import com.example.androiddisplayjokeslibrary.DisplayJokeActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;
import java.io.IOException;

class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String> {

  public static final String LOG_TAG = EndpointsAsyncTask.class.getCanonicalName();

  private static MyApi myApiService = null;

  public View mView;

  public Context mContext;
  private ProgressBar progressBar;


  public EndpointsAsyncTask(Context context, View view) {
    this.mContext = context;
    this.mView = view;

  }

  @Override
  protected String doInBackground(Pair<Context, String>... params) {

    if (myApiService == null) {  // Only do this once
      MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
          new AndroidJsonFactory(), null)
          // options for running against local devappserver
          // - 10.0.2.2 is localhost's IP address in Android emulator
          // - turn off compression when running against local devappserver
          .setRootUrl("http://172.29.3.140:8080/_ah/api/")
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
      Log.i(LOG_TAG, " TEST**** The Joke Retrieved is: " + joke);

      return joke;
    } catch (IOException e) {
      return e.getMessage();
    }
  }

  @Override
  protected void onPreExecute() {
    super.onPreExecute();

    progressBar = mView.findViewById(R.id.progress_bar);

    progressBar.setVisibility(View.VISIBLE);

  }

  @Override
  protected void onPostExecute(final String mResult) {

    //Add a 3 second delay before showing the result
    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {

        Log.i(LOG_TAG, " TEST **** The Joke Retrieved is: " + mResult);

        Intent intent = new Intent(mContext, DisplayJokeActivity.class);
        // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(DisplayJokeActivity.JOKE_INTENT_TAG, mResult);
        mContext.startActivity(intent);

        progressBar.setVisibility(View.INVISIBLE);

      }
    }, 3000);


  }

  public interface AsyncTaskCallback {
    void callBack(String joke);
  }
}
