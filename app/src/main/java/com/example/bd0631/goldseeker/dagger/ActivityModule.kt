package com.example.bd0631.goldseeker.dagger

import com.example.bd0631.goldseeker.additems.AddNewItemsActivity
import com.example.bd0631.goldseeker.additems.AddNewItemsModule
import com.example.bd0631.goldseeker.throwAwayItemsList.ThrowAwayItemListActivity
import com.example.bd0631.goldseeker.throwAwayItemsList.ThrowAwayItemListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

  @ActivityScoped
  @ContributesAndroidInjector(modules = [(AddNewItemsModule::class)])
  abstract fun AddNewItemsActivity(): AddNewItemsActivity

  @ActivityScoped
  @ContributesAndroidInjector(modules = [(ThrowAwayItemListModule::class)])
  abstract fun ThrowAwayItemListActivity(): ThrowAwayItemListActivity

}