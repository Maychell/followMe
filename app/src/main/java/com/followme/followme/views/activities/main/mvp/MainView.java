package com.followme.followme.views.activities.main.mvp;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.followme.followme.R;
import com.followme.followme.views.fragments.FollowMeMapFragment;
import com.followme.followme.views.fragments.profile.ProfileFragment;
import com.jakewharton.rxbinding.view.RxView;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;

/**
 * Created by maychellfernandesdeoliveira on 03/12/2017.
 */

@SuppressLint("ViewConstructor")
public class MainView extends FrameLayout {

    @BindView(R.id.btn_home)    ImageView btnHome;
    @BindView(R.id.btn_friends) ImageView btnFriends;
    @BindView(R.id.btn_profile) ImageView btnProfile;

    private FollowMeMapFragment mMapFragment = new FollowMeMapFragment();
    private ProfileFragment mProfileFragment = new ProfileFragment();

    public MainView(FragmentActivity activity) {
        super(activity);
        inflate(getContext(), R.layout.activity_main, this);
        ButterKnife.bind(this);

        activity.getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content_fragment, mMapFragment).commit();
    }

    public Observable<Void> observeHomeButton() {
        return RxView.clicks(btnHome);
    }

    public Observable<Void> observeFriendsButton() {
        return RxView.clicks(btnFriends);
    }

    public Observable<Void> observeProfileButton() {
        return RxView.clicks(btnProfile);
    }

    public void renderHomeFragment() {
        updateFragmentView(mMapFragment);
    }

    public void renderFriendsFragment() {
        updateFragmentView(mProfileFragment);
    }

    public void renderProfileFragment() {
        updateFragmentView(mProfileFragment);
    }

    private void updateFragmentView(Fragment fragment) {
        FragmentManager fragmentManager = ((FragmentActivity) getContext()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.content_fragment, fragment);
        fragmentTransaction.commit();
    }
}
