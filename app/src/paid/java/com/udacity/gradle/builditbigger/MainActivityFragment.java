package com.udacity.gradle.builditbigger;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private View root;
    private ProgressBar progressBar;
    private Button button;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_main, container, false);

        progressBar = root.findViewById(R.id.progress_bar);
        button = root.findViewById(R.id.btn_tell_joke);

        progressBar.setVisibility(View.INVISIBLE);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);

                EndpointsAsyncTask endpointsAsyncTask = new EndpointsAsyncTask(getContext(), root);

                endpointsAsyncTask.execute();

            }
        });

        return root;

    }
}
