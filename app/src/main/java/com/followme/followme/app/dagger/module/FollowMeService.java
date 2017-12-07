package com.followme.followme.app.dagger.module;

import com.followme.followme.utils.Constants;
import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by maychellfernandesdeoliveira on 27/11/2017.
 */

public interface FollowMeService {
    @POST(Constants.Service.SIGN_IN)
    Observable<JsonObject> signIn(@QueryMap Map<String, String> partMap);
}
