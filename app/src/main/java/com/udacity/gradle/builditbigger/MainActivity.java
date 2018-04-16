package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.androiddisplayjokeslibrary.DisplayJokeActivity;
import com.udacity.gradle.builditbigger.EndpointsAsyncTask.AsyncTaskCallback;


public class MainActivity extends AppCompatActivity implements
    EndpointsAsyncTask.AsyncTaskCallback {

    private String MAIN_ACTIVITY_LOG_TAG = MainActivity.class.getName();
    public Context mContext;
    AsyncTaskCallback asyncTaskCallback = new AsyncTaskCallback() {
        @Override
        public void callBack(String joke) {

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

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

    public void tellJoke(View view) {

        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(mContext);

        endpointsAsyncTask.execute();

        Toast.makeText(this, "The joke is on its way hold on tight!", Toast.LENGTH_SHORT)
            .show();


    }


}
