package com.example.bd0631.goldseeker.details

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.graphics.Bitmap
import com.example.bd0631.goldseeker.database.PickUpLacationsRepo
import com.example.bd0631.goldseeker.database.PickUpLocation
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val pickUpLacationsRepo: PickUpLacationsRepo
): ViewModel() {


  var imageFile: Bitmap? = null
  var throwAwayItem: MutableLiveData<PickUpLocation>? = null


  fun getThrowAwayItem(pickUpLocation: PickUpLocation): LiveData<PickUpLocation>? {
    if (throwAwayItem == null) {
      throwAwayItem = MutableLiveData()
      loadThrowAwayItem(pickUpLocation)
    }
    return throwAwayItem
  }

  fun loadThrowAwayItem(pickUpLocation: PickUpLocation) {
    pickUpLacationsRepo.getSinglePickUpLocation(pickUpLocation.id)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : Observer<PickUpLocation> {

          override fun onComplete() {

          }

          override fun onSubscribe(d: Disposable) {
          }

          override fun onNext(t: PickUpLocation) {
            throwAwayItem?.value = t
          }

          override fun onError(e: Throwable) {

          }
        })
  }


}