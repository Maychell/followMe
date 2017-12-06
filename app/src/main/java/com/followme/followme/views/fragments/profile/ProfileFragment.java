package com.followme.followme.views.fragments.profile;

import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.followme.followme.app.dagger.module.ContextModule;
import com.followme.followme.views.fragments.profile.dagger.DaggerProfileComponent;
import com.followme.followme.views.fragments.profile.dagger.ProfileModule;
import com.followme.followme.views.fragments.profile.mvp.ProfilePresenter;
import com.followme.followme.views.fragments.profile.mvp.ProfileView;

import javax.inject.Inject;

/**
 * A login screen that offers login via Google.
 */
public class ProfileFragment extends Fragment {

    @Inject ProfileView mView;
    @Inject ProfilePresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DaggerProfileComponent.builder()
                .contextModule(new ContextModule(getContext()))
                .profileModule(new ProfileModule(this))
                .build()
                .inject(this);
        mPresenter.create();
        return mView;
    }

    @Override
    public void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode, data);
    }
}

