package com.followme.followme.app.mvp;

import com.followme.followme.app.dagger.module.FollowMeService;
import com.google.gson.JsonObject;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;

/**
 * Created by maychellfernandesdeoliveira on 03/12/2017.
 */

public class FollowMeAppModel {

    private static final String ID_TOKEN = "id_token";

    private final FollowMeService mService;

    public FollowMeAppModel(FollowMeService service) {
        mService = service;
    }

    Observable<JsonObject> signInAPI(String idToken) {
        Map<String, String> hash = new HashMap<>();
        hash.put(ID_TOKEN, idToken);
        return mService.signIn(hash);
    }
}
