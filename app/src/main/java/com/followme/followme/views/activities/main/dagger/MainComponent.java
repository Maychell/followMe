package com.followme.followme.views.activities.main.dagger;

import com.followme.followme.views.activities.main.MainActivity;

import dagger.Component;

/**
 * Created by maychellfernandesdeoliveira on 03/12/2017.
 */

@MainScope
@Component(modules = { MainModule.class })
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
