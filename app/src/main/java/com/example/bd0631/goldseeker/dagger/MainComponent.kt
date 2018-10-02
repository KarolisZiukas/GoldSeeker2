package com.example.bd0631.goldseeker.dagger

import android.app.Application
import com.example.bd0631.goldseeker.GoldApplication
import com.example.karolis.logginhours.dagger.RoomModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
  (AppModule::class),
  (RoomModule::class),
  (FireStorageModule::class),
  (AndroidSupportInjectionModule::class),
  (ActivityModule::class)])
interface MainComponent : AndroidInjector<GoldApplication> {

  @Component.Builder
  interface Builder {

    @BindsInstance
    fun application(application: Application): MainComponent.Builder

    fun build(): MainComponent
  }
}