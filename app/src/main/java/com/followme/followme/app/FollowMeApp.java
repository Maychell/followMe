package com.followme.followme.app;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import com.followme.followme.app.dagger.module.FollowMeAppModule;
import com.followme.followme.models.User;
import com.followme.followme.app.mvp.FollowMeAppPresenter;
import com.followme.followme.app.dagger.component.AppComponent;
import com.followme.followme.app.dagger.component.DaggerAppComponent;
import com.followme.followme.app.dagger.module.ContextModule;
import com.followme.followme.utils.PreferencesManager;

import javax.inject.Inject;

/**
 * Created by maychellfernandesdeoliveira on 26/11/2017.
 */

public class FollowMeApp extends Application {

    public static PreferencesManager prefs;
    private static FollowMeApp instance;
    private User mUser;
    private AppComponent mComponent;

    @Inject FollowMeAppPresenter mPresenter;

    public static FollowMeApp getInstance() {
        return FollowMeApp.instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FollowMeApp.instance = this;

        PreferencesManager.initializeInstance(this);
        prefs = PreferencesManager.getInstance();

        mComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .followMeAppModule(new FollowMeAppModule(this))
                .build();

        mComponent.inject(this);
        mPresenter.create();
    }

    public boolean isUserLogged() {
        return mUser != null;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }

    public Context getContext() {
        return this;
    }

    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public AppComponent getComponent() {
        return mComponent;
    }
}
