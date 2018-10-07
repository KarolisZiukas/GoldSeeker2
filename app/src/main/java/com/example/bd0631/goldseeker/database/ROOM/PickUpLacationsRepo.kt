package com.example.bd0631.goldseeker.database


import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PickUpLacationsRepo @Inject constructor(private val pickUpLocationDao: PickUpLocationDao) {

  fun getAllPickUpLocations(): Observable<List<PickUpLocation>> {
    return pickUpLocationDao.getPickUpLocations()
        .toObservable()
  }

  fun insertPickUpLocations(pickUpLocation: PickUpLocation) {
    pickUpLocationDao.insertNewPickUpItems(pickUpLocation)
  }

  fun removePickUpLocation(id: Long?){
    pickUpLocationDao.removePickUpLocation(id)
  }


}