package com.followme.followme.models;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by MaychellFernandes on 01/12/17.
 */

public class UserTest {
    private final String NAME = "foo bar";
    private final String EMAIL = "foo@bar.com";
    private final String IMAGE_PATH = "foo_bar.jpg";

    private User mUser;

    @Before
    public void setUp() {
        mUser = new User(NAME, EMAIL, IMAGE_PATH);
    }

    @Test
    public void testGetName() {
        Assert.assertEquals(mUser.getName(), NAME);
    }

    @Test
    public void testGetEmail() {
        Assert.assertEquals(mUser.getEmail(), EMAIL);
    }

    @Test
    public void testGetImagePath() {
        Assert.assertEquals(mUser.getImagePath(), IMAGE_PATH);
    }

    @Test
    public void testSetImagePath() {
        String newImagePath = "new_image.png";
        mUser.setImagePath(newImagePath);
        Assert.assertEquals(mUser.getImagePath(), newImagePath);
    }
}
