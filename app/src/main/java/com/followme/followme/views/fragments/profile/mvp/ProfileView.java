package com.followme.followme.views.fragments.profile.mvp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.followme.followme.R;
import com.followme.followme.models.User;
import com.google.android.gms.common.SignInButton;
import com.jakewharton.rxbinding.view.RxView;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import rx.Observable;

/**
 * Created by maychellfernandesdeoliveira on 04/12/2017.
 */

@SuppressLint("ViewConstructor")
public class ProfileView extends FrameLayout {

    private final Fragment mFragment;

    @BindView(R.id.login_progress) ProgressBar mProgressBar;
    @BindView(R.id.btn_sign_in) SignInButton mBtnSignIn;

    // User references.
    @BindView(R.id.user_layout) LinearLayout mUserLayout;
    @BindView(R.id.user_avatar) ImageView mUserAvatar;
    @BindView(R.id.user_name) TextView mUserName;
    @BindView(R.id.user_email) TextView mUserEmail;

    @BindInt(android.R.integer.config_shortAnimTime) int shortAnimTime;

    public ProfileView(Fragment fragment) {
        super(fragment.getContext());
        inflate(getContext(), R.layout.fragment_profile, this);
        ButterKnife.bind(this);

        mFragment = fragment;
    }

    public void hideSignInButton() {
        mBtnSignIn.setVisibility(View.GONE);
    }

    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void displayLoggedUser(User user) {
        mUserLayout.setVisibility(View.VISIBLE);
        mUserName.setText(user.getName());
        mUserEmail.setText(user.getEmail());

        Glide.with(getContext())
                .load(user.getImagePath())
                .bitmapTransform(new CropCircleTransformation(getContext()))
                .placeholder(R.mipmap.ic_avatar_placeholder)
                .crossFade()
                .into(mUserAvatar);
    }

    public Observable<Void> observeLoginButton() {
        return RxView.clicks(mBtnSignIn);
    }

    public void openActivityForResult(Intent intent, int id) {
        mFragment.startActivityForResult(intent, id);
    }

    public void showProgress() {
        mBtnSignIn.setVisibility(View.GONE);
        mBtnSignIn.animate()
                .setDuration(shortAnimTime)
                .alpha(0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mBtnSignIn.setVisibility(View.GONE);
                    }
                });

        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.animate()
                .setDuration(shortAnimTime)
                .alpha(1)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mProgressBar.setVisibility(View.VISIBLE);
                    }
                });
    }

    public void hideProgress() {
        mBtnSignIn.setVisibility(View.VISIBLE);
        mBtnSignIn.animate()
                .setDuration(shortAnimTime)
                .alpha(0)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mBtnSignIn.setVisibility(View.VISIBLE);
                    }
                });

        mProgressBar.setVisibility(View.GONE);
        mProgressBar.animate()
                .setDuration(shortAnimTime)
                .alpha(1)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mProgressBar.setVisibility(View.GONE);
                    }
                });
    }
}
