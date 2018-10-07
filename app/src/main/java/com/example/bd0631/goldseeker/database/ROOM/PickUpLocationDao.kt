package com.example.bd0631.goldseeker.database


import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import io.reactivex.Single

@Dao
interface PickUpLocationDao {

  @Query("SELECT * FROM pickUpLocations ORDER BY id")
  fun getPickUpLocations(): Single<List<PickUpLocation>>

  @Insert
  fun insertNewPickUpItems(pickUpLocation: PickUpLocation)

  @Query("DELETE FROM pickUpLocations WHERE id = :id")
  fun removePickUpLocation(id: Long?)
}