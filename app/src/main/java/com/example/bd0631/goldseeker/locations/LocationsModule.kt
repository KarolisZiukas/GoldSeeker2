package com.example.bd0631.goldseeker.locations

import com.example.bd0631.goldseeker.dagger.FragmentScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module

abstract class LocationsModule {

  @FragmentScoped
  @ContributesAndroidInjector
  abstract fun locationsFragment(): LocationsFragment

}
