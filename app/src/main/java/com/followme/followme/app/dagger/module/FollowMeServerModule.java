package com.followme.followme.app.dagger.module;

import com.followme.followme.utils.Constants;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MaychellFernandes on 27/11/17.
 */

@Module(includes = NetworkModule.class)
public class FollowMeServerModule {

    @Provides
    @FollowMeServerScope
    FollowMeService getFollowMeServer(Retrofit retrofit) {
        return retrofit.create(FollowMeService.class);
    }

    @Provides
    @FollowMeServerScope
    Retrofit retrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(Constants.Service.SERVICE_URL)
                .build();
    }
}
