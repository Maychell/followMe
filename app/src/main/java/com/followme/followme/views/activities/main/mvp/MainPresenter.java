package com.followme.followme.views.activities.main.mvp;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by maychellfernandesdeoliveira on 03/12/2017.
 */

public class MainPresenter {

    private final MainView mView;
    private final CompositeSubscription compositeSubscription = new CompositeSubscription();

    public MainPresenter(MainView view) {
        mView = view;
    }

    public void attachView() {
        compositeSubscription.add(observeBtnHome());
        compositeSubscription.add(observeBtnFriends());
        compositeSubscription.add(observeBtnProfile());
    }

    public void detachView() {
        compositeSubscription.clear();
    }

    private Subscription observeBtnHome() {
        return mView.observeHomeButton().subscribe(__ -> mView.renderHomeFragment());
    }

    private Subscription observeBtnFriends() {
        return mView.observeFriendsButton().subscribe(__ -> mView.renderFriendsFragment());
    }

    private Subscription observeBtnProfile() {
        return mView.observeProfileButton().subscribe(__ -> mView.renderProfileFragment());
    }
}
