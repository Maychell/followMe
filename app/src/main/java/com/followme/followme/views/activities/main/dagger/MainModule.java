package com.followme.followme.views.activities.main.dagger;

import android.support.v4.app.FragmentActivity;

import com.followme.followme.views.activities.main.mvp.MainPresenter;
import com.followme.followme.views.activities.main.mvp.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maychellfernandesdeoliveira on 03/12/2017.
 */

@Module
public class MainModule {

    private final FragmentActivity mActivity;

    public MainModule(FragmentActivity activity) {
        this.mActivity = activity;
    }

    @Provides
    @MainScope
    public MainView view() {
        return new MainView(mActivity);
    }

    @Provides
    @MainScope
    public MainPresenter presenter(MainView view) {
        return new MainPresenter(view);
    }
}
