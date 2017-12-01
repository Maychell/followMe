package com.followme.followme.app.mvp;

import com.followme.followme.app.dagger.module.FollowMeService;

/**
 * Created by maychellfernandesdeoliveira on 03/12/2017.
 */

public class FollowMeAppModel {

    private final FollowMeService mService;

    public FollowMeAppModel(FollowMeService service) {
        mService = service;
    }
}
