package com.example.karolis.logginhours.dagger

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import com.example.bd0631.goldseeker.CustomViewModelFactory
import com.example.bd0631.goldseeker.database.ROOM.AppsDatabase
import com.example.bd0631.goldseeker.database.PickUpLacationsRepo
import com.example.bd0631.goldseeker.database.PickUpLocationDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class RoomModule {

    @Module
    companion object {
        @Provides
        @Singleton
        @JvmStatic
        fun provideShiftsDataBase(application: Application): AppsDatabase {
            return Room.databaseBuilder(
                    application,
                    AppsDatabase::class.java,
                    "GoldSeeker.db").build()
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideShiftsDao(dataBase: AppsDatabase): PickUpLocationDao {
            return dataBase.pickUpLocationsDao()
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideShiftsRepo(dataBase: AppsDatabase): PickUpLacationsRepo {
            return PickUpLacationsRepo(dataBase.pickUpLocationsDao())
        }

        @Provides
        @Singleton
        @JvmStatic
        fun provideViewModelFactory(pickUpLacationsRepo: PickUpLacationsRepo): ViewModelProvider.Factory = CustomViewModelFactory(pickUpLacationsRepo)

    }


}
