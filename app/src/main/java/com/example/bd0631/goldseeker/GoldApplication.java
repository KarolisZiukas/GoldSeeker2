package com.example.bd0631.goldseeker;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.example.bd0631.goldseeker.dagger.DaggerMainComponent;
import com.example.bd0631.goldseeker.database.PickUpLacationsRepo;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;


public class GoldApplication extends DaggerApplication implements HasActivityInjector {

    //Todo clean UP!!!!
    //Todo fix entity
    //ToDo fix layouts
    //ToDo add 3 more items
    //ToDo make ui a bit nicer
    //ToDo try to fix threading
    @Inject DispatchingAndroidInjector<Fragment> fragmentInjector;

    @Inject PickUpLacationsRepo pickUpLacationsRepo;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerMainComponent.builder().application(this).build();
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return super.activityInjector();
    }
}
