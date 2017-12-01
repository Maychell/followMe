package com.followme.followme.app.dagger.component;

import com.followme.followme.app.FollowMeApp;
import com.followme.followme.app.dagger.module.FollowMeAppModule;
import com.followme.followme.app.dagger.module.FollowMeServerScope;

import dagger.Component;

/**
 * Created by MaychellFernandes on 27/11/17.
 */

@FollowMeServerScope
@Component(modules = { FollowMeAppModule.class})
public interface AppComponent {
    void inject(FollowMeApp followMeApp);
}
