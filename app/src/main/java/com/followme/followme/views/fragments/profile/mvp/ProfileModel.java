package com.followme.followme.views.fragments.profile.mvp;

import com.followme.followme.app.dagger.module.FollowMeService;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * Created by maychellfernandesdeoliveira on 04/12/2017.
 */

public class ProfileModel {

    private static final String ID_TOKEN = "id_token";

    private final FollowMeService mService;

    public ProfileModel(FollowMeService service) {
        mService = service;
    }

    Observable<JsonObject> signInAPI(String idToken) {
        Map<String, String> hash = new HashMap<>();
        hash.put(ID_TOKEN, idToken);
        return mService.signIn(hash);
    }
}
