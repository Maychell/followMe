package com.followme.followme.app.dagger.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MaychellFernandes on 27/11/17.
 */

@Module
public class ContextModule {

    private final Context mContext;

    public ContextModule(Context context) {
        mContext = context;
    }

    @Provides
    @FollowMeServerScope
    public Context context() {
        return mContext;
    }
}
