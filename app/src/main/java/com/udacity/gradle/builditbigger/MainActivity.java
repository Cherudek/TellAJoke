package com.udacity.gradle.builditbigger;

import static com.example.androiddisplayjokeslibrary.DisplayJokeActivity.JOKE_INTENT_TAG;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.androiddisplayjokeslibrary.DisplayJokeActivity;


public class MainActivity extends AppCompatActivity implements
    EndpointsAsyncTask.asyncTaskCallback {

    private String MAIN_ACTIVITY_LOG_TAG = MainActivity.class.getName();

    private String myJoke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(MAIN_ACTIVITY_LOG_TAG, "The Joke is: " + myJoke);


    }


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


    public void tellJoke(View view) {

        EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask();

        endpointsAsyncTask.execute();

        Toast.makeText(this, "The joke is on its way hold on tight!" + myJoke, Toast.LENGTH_SHORT)
            .show();
    }


    @Override
    public void callBackCall(String myJoke) {

        Intent intent = new Intent(this, DisplayJokeActivity.class);
        intent.putExtra(JOKE_INTENT_TAG, myJoke);
        startActivity(intent);

    }
}
