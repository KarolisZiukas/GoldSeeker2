package com.example.bd0631.goldseeker.database.ROOM

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.bd0631.goldseeker.database.PickUpLocationDao


@Database(entities = [(PickUpLocation::class)], version = 1)
public abstract class AppsDatabase: RoomDatabase(){

    abstract fun pickUpLocationsDao(): PickUpLocationDao

}

