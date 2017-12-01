package com.followme.followme.app.dagger.module;

import com.followme.followme.app.FollowMeApp;
import com.followme.followme.app.mvp.FollowMeAppModel;
import com.followme.followme.app.mvp.FollowMeAppPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maychellfernandesdeoliveira on 03/12/2017.
 */

@Module(includes = FollowMeServerModule.class)
public class FollowMeAppModule {
    private final FollowMeApp mFollowMeApp;

    public FollowMeAppModule(FollowMeApp followMeApp) {
        this.mFollowMeApp = followMeApp;
    }

    @Provides
    @FollowMeServerScope
    public FollowMeAppModel model(FollowMeService service) {
        return new FollowMeAppModel(service);
    }

    @Provides
    @FollowMeServerScope
    public FollowMeAppPresenter presenter(FollowMeAppModel model) {
        return new FollowMeAppPresenter(mFollowMeApp, model);
    }
}
