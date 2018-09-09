package com.followme.followme.views.activities.main;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.followme.followme.views.activities.main.dagger.DaggerMainComponent;
import com.followme.followme.views.activities.main.dagger.MainModule;
import com.followme.followme.views.activities.main.mvp.MainPresenter;
import com.followme.followme.views.activities.main.mvp.MainView;

import javax.inject.Inject;

public class MainActivity extends FragmentActivity {

    @Inject MainView mView;
    @Inject MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);

        setContentView(mView);
        mPresenter.attachView();
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }
}
