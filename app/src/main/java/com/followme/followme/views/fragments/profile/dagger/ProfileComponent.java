package com.followme.followme.views.fragments.profile.dagger;

import com.followme.followme.app.dagger.module.FollowMeServerScope;
import com.followme.followme.views.fragments.profile.ProfileFragment;

import dagger.Component;

/**
 * Created by maychellfernandesdeoliveira on 04/12/2017.
 */

@FollowMeServerScope
@Component(modules = ProfileModule.class)
public interface ProfileComponent {
    void inject(ProfileFragment profileFragment);
}
