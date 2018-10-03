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
    //Todo Database
    //Todo screens
    //Todo photos
    //Todo Maps
    //Todo 4 screens
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
