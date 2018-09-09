package com.followme.followme.views.mvp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by maychellfernandesdeoliveira on 05/12/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    private MainPresenter mPresenter;
    @Mock private MainInterface mView;

    @Before
    public void setUp() {
        mPresenter = new MainPresenter(mView);
    }

    @Test
    public void renderHomeFragment() {
        mPresenter.clickBtnHome();
        Mockito.verify(mView, Mockito.times(1)).renderHomeFragment();
    }

    @Test
    public void renderFriendsFragment() {
        mPresenter.clickBtnFriends();
        Mockito.verify(mView, Mockito.times(1)).renderFriendsFragment();
    }

    @Test
    public void renderProfileFragment() {
        mPresenter.clickBtnProfile();
        Mockito.verify(mView, Mockito.times(1)).renderProfileFragment();
    }
}
