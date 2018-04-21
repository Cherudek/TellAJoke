package com.udacity.gadle.builditbigger;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.example.androiddisplayjokeslibrary.DisplayJokeActivity;
import com.udacity.gradle.builditbigger.MainActivity;
import com.udacity.gradle.builditbigger.R;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EndpointAsyncTaskTest {

  private static final String joke = "Of course I should clean my windows. But privacy is important too.";


  @Rule
  public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
      MainActivity.class);

  @Rule
  public ActivityTestRule<DisplayJokeActivity> mDisplayActivityRule = new ActivityTestRule<>(
      DisplayJokeActivity.class);

  @Test
  public void mainActivityGetJokeButton() {

    onView(withId(R.id.btn_tell_joke)).perform(click());

    onView(withId(R.id.my_first_joke)).check(matches(withText(joke)));


  }

}
