package com.example.bd0631.goldseeker.details

import com.example.bd0631.goldseeker.dagger.FragmentScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module

abstract class DetailsModule {

  @FragmentScoped
  @ContributesAndroidInjector
  abstract fun detailsFragment(): DetailsFragment

}