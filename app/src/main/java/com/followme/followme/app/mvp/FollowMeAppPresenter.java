package com.followme.followme.app.mvp;

import com.followme.followme.app.FollowMeApp;

/**
 * Created by maychellfernandesdeoliveira on 01/12/2017.
 */

public class FollowMeAppPresenter {

    private FollowMeApp mView;
    private FollowMeAppModel mModel;

    public FollowMeAppPresenter(FollowMeApp view, FollowMeAppModel model) {
        mView = view;
        mModel = model;
    }

    public void create() {
    }
}
