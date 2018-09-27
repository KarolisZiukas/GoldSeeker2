package com.example.bd0631.goldseeker.additems

import com.example.bd0631.goldseeker.dagger.FragmentScoped
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AddNewItemsModule {

  @FragmentScoped
  @ContributesAndroidInjector
  abstract fun addNewItemsFragment(): AddNewItemsFragment
}