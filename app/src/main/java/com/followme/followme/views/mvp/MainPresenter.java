package com.followme.followme.views.mvp;

public class MainPresenter {

    private final MainInterface mView;

    public MainPresenter(MainInterface view) {
        mView = view;
    }

    public void clickBtnHome() {
        mView.renderHomeFragment();
    }

    public void clickBtnFriends() {
        mView.renderFriendsFragment();
    }

    public void clickBtnProfile() {
        mView.renderProfileFragment();
    }
}
