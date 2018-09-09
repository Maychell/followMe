package com.followme.followme.views.activities.main;

import com.followme.followme.views.activities.main.mvp.MainPresenter;
import com.followme.followme.views.activities.main.mvp.MainView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;
import rx.android.plugins.RxAndroidPlugins;

/**
 * Created by maychellfernandesdeoliveira on 05/12/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @InjectMocks private MainPresenter mPresenter;
    @Mock private MainView mView;

    @Before
    public void setUp() {
        mPresenter = new MainPresenter(mView);

        Mockito.when(mView.observeHomeButton()).thenReturn(Observable.never());
        Mockito.when(mView.observeFriendsButton()).thenReturn(Observable.never());
        Mockito.when(mView.observeProfileButton()).thenReturn(Observable.never());
    }

    @Test
    public void renderHomeFragment() {
        Mockito.when(mView.observeHomeButton()).thenReturn(Observable.just(null));
        mPresenter.attachView();
        Mockito.verify(mView, Mockito.times(1)).renderHomeFragment();
    }

    @Test
    public void renderFriendsFragment() {
        Mockito.when(mView.observeFriendsButton()).thenReturn(Observable.just(null));
        mPresenter.attachView();
        Mockito.verify(mView, Mockito.times(1)).renderFriendsFragment();
    }

    @Test
    public void renderProfileFragment() {
        Mockito.when(mView.observeProfileButton()).thenReturn(Observable.just(null));
        mPresenter.attachView();
        Mockito.verify(mView, Mockito.times(1)).renderProfileFragment();
    }

    @After
    public void tearDown() {
        RxAndroidPlugins.getInstance().reset();
    }
}
