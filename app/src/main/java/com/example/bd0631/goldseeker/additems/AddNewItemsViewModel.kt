package com.example.bd0631.goldseeker.additems

import android.arch.lifecycle.ViewModel
import android.util.Log
import com.example.bd0631.goldseeker.database.PickUpLacationsRepo
import com.example.bd0631.goldseeker.database.PickUpLocation
import com.example.karolis.logginhours.widgets.Generator
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddNewItemsViewModel @Inject constructor(val pickUpLocationsRepo: PickUpLacationsRepo): ViewModel() {

 lateinit var addNewItemsNavigator: AddNewItemsNavigator

  fun setNavigator(addNewItemsNavigator: AddNewItemsNavigator) {
    this.addNewItemsNavigator = addNewItemsNavigator
  }

  fun saveThrowAwayItems() {
      Completable.fromAction{
          pickUpLocationsRepo
              .insertPickUpLocations(PickUpLocation(Generator.generateId()))}
          .observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.newThread())
          .subscribe(object: CompletableObserver {
            override fun onComplete() {
                  addNewItemsNavigator.onNewPickUpItemSaved()
            }

            override fun onSubscribe(d: Disposable) {
            }

            override fun onError(e: Throwable) {
              Log.d("SAVING ERROR", e.message)
            }
          })
  }
}