package com.wakeel.moviesapp.di.component;

import com.wakeel.moviesapp.di.module.ActivityModule;
import com.wakeel.moviesapp.di.scope.ConfigPersistent;

import dagger.Component;

@ConfigPersistent
@Component(dependencies = AppComponent.class)
public interface ConfigPersistentComponent {

    ActivityComponent activityComponent(ActivityModule activityModule);

}
