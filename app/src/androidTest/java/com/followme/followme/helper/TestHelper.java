package com.followme.followme.helper;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;

import static org.hamcrest.CoreMatchers.not;

/**
 * Created by MaychellFernandes on 06/12/17.
 */

public class TestHelper {
    public static void resourceNotDisplayed(int resourceId) {
        Espresso.onView(ViewMatchers.withId(resourceId))
                .check(ViewAssertions.matches(not(ViewMatchers.isDisplayed())));
    }

    public static void resourceDisplayed(int resourceId) {
        Espresso.onView(ViewMatchers.withId(resourceId))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    public static void resourceDoesNotExist(int resourceId) {
        Espresso.onView(ViewMatchers.withId(resourceId))
                .check(ViewAssertions.doesNotExist());
    }

    public static void resourceMatchText(int resourceId, String text) {
        Espresso.onView(ViewMatchers.withId(resourceId))
                .check(ViewAssertions.matches(ViewMatchers.withText(text)));
    }
}
