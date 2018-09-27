package com.example.bd0631.goldseeker.dagger

import com.example.bd0631.goldseeker.additems.AddNewItemsActivity
import com.example.bd0631.goldseeker.additems.AddNewItemsModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

  @ActivityScoped
  @ContributesAndroidInjector(modules = [(AddNewItemsModule::class)])
  abstract fun AddNewItemsActivity(): AddNewItemsActivity

}