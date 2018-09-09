package com.followme.followme.views.mvc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.followme.followme.R;
import com.followme.followme.views.fragments.FollowMeMapFragment;
import com.followme.followme.views.fragments.profile.ProfileFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    private FollowMeMapFragment mMapFragment = new FollowMeMapFragment();
    private ProfileFragment mProfileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content_fragment, mMapFragment).commit();
    }

    @OnClick(R.id.btn_home)
    public void renderHomeFragment() {
        updateFragmentView(mMapFragment);
    }

    @OnClick(R.id.btn_friends)
    public void renderFriendsFragment() {
        updateFragmentView(mProfileFragment);
    }

    @OnClick(R.id.btn_profile)
    public void renderProfileFragment() {
        updateFragmentView(mProfileFragment);
    }

    private void updateFragmentView(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.content_fragment, fragment);
        fragmentTransaction.commit();
    }
}