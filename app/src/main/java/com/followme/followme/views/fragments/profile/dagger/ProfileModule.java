package com.followme.followme.views.fragments.profile.dagger;

import android.support.v4.app.Fragment;

import com.followme.followme.app.FollowMeApp;
import com.followme.followme.app.dagger.module.FollowMeServerModule;
import com.followme.followme.app.dagger.module.FollowMeServerScope;
import com.followme.followme.app.dagger.module.FollowMeService;
import com.followme.followme.models.User;
import com.followme.followme.utils.Constants;
import com.followme.followme.views.fragments.profile.mvp.ProfileModel;
import com.followme.followme.views.fragments.profile.mvp.ProfilePresenter;
import com.followme.followme.views.fragments.profile.mvp.ProfileView;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import dagger.Module;
import dagger.Provides;

/**
 * Created by maychellfernandesdeoliveira on 04/12/2017.
 */

@Module(includes = FollowMeServerModule.class)
public class ProfileModule {

    private final Fragment mFragment;

    public ProfileModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @FollowMeServerScope
    ProfileView profileView() {
        return new ProfileView(mFragment);
    }

    @Provides
    @FollowMeServerScope
    ProfileModel profileModel(FollowMeService service) {
        return new ProfileModel(service);
    }

    @Provides
    @FollowMeServerScope
    ProfilePresenter profilePresenter(ProfileModel model, ProfileView view, GoogleSignInOptions googleSignInOptions) {
        User user = FollowMeApp.getInstance().getUser();
        return new ProfilePresenter(model, view, user, googleSignInOptions);
    }

    @Provides
    @FollowMeServerScope
    GoogleSignInOptions googleSignInAccount() {
        return new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(Constants.SERVER_CLIENT_ID)
                .requestEmail()
                .build();
    }
}
