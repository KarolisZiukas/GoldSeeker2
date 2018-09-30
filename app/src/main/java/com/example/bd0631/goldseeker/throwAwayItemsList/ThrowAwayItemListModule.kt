package com.example.bd0631.goldseeker.throwAwayItemsList

import com.example.bd0631.goldseeker.dagger.FragmentScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ThrowAwayItemListModule {

  @FragmentScoped
  @ContributesAndroidInjector
  abstract fun throwAwayItemsList(): ThrowAwayItemListFragment

}