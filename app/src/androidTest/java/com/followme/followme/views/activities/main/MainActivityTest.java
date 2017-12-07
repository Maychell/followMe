package com.followme.followme.views.activities.main;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;

import com.followme.followme.R;
import com.followme.followme.helper.TestHelper;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by MaychellFernandes on 06/12/17.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public IntentsTestRule<MainActivity> mMainActivity =
            new IntentsTestRule<>(MainActivity.class);

    @Test
    public void defaultMapFragmentDisplay() {
        TestHelper.resourceDoesNotExist(R.id.profile_fragment);
        TestHelper.resourceDisplayed(R.id.map_fragment);
    }

    @Test
    public void clickProfileFragmentButton() {
        // Click profile button
        Espresso.onView(ViewMatchers.withId(R.id.btn_profile)).perform(ViewActions.click());

        TestHelper.resourceDoesNotExist(R.id.map_fragment);
        TestHelper.resourceDisplayed(R.id.profile_fragment);
    }
}
