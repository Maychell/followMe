package com.followme.followme.views.fragments.profile;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.followme.followme.R;
import com.followme.followme.app.FollowMeApp;
import com.followme.followme.helper.TestHelper;
import com.followme.followme.models.User;
import com.followme.followme.views.activities.main.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by maychellfernandesdeoliveira on 07/12/2017.
 */

@RunWith(AndroidJUnit4.class)
public class ProfileFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivity = new ActivityTestRule<>(MainActivity.class);

    private final String USER_NAME = "FOO BAR";
    private final String USER_EMAIL = "foo@bar.com";
    private User mUser = new User(USER_NAME, USER_EMAIL);

    private void setUptUserLogged() {
        FollowMeApp.getInstance().setUser(mUser);
        // Click profile button to display profile fragment
        Espresso.onView(ViewMatchers.withId(R.id.btn_profile)).perform(ViewActions.click());
    }

    private void setUpNoUserLogged() {
        FollowMeApp.getInstance().setUser(null);
        // Click profile button to display profile fragment
        Espresso.onView(ViewMatchers.withId(R.id.btn_profile)).perform(ViewActions.click());
    }

    @Test
    public void whenNoLoggedUserBtnLoginDisplayed() {
        setUpNoUserLogged();

        TestHelper.resourceDisplayed(R.id.btn_sign_in);
    }

    @Test
    public void whenLoggedUserBtnLoginNotDisplayed() {
        setUptUserLogged();

        TestHelper.resourceNotDisplayed(R.id.btn_sign_in);
    }

    @Test
    public void whenLoggedUserDisplayProfile() {
        setUptUserLogged();

        TestHelper.resourceDisplayed(R.id.user_layout);
        TestHelper.resourceDisplayed(R.id.user_avatar);
        TestHelper.resourceDisplayed(R.id.user_name);
        TestHelper.resourceDisplayed(R.id.user_email);
    }

    @Test
    public void whenLoggedUserDisplayProfileData() {
        setUptUserLogged();

        TestHelper.resourceMatchText(R.id.user_name, USER_NAME);
        TestHelper.resourceMatchText(R.id.user_email, USER_EMAIL);
    }
}
