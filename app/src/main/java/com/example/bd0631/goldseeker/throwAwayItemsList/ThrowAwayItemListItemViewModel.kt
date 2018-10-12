package com.example.bd0631.goldseeker.throwAwayItemsList

import android.arch.lifecycle.ViewModel
import android.graphics.Bitmap
import com.example.bd0631.goldseeker.database.PickUpLacationsRepo
import com.example.bd0631.goldseeker.database.PickUpLocation
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ThrowAwayItemListItemViewModel(
    val pickUpLocation: PickUpLocation?,
    val pickUpLacationsRepo: PickUpLacationsRepo,
    val imageFile: Bitmap?,
    val throwAwayItemListCallback: ThrowAwayItemListCallback) : ViewModel() {


  fun removeItem() {
    Completable.fromAction {
      pickUpLacationsRepo.removePickUpLocation(pickUpLocation?.id)
    }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.newThread())
        .subscribe(object : CompletableObserver {
          override fun onComplete() {
            throwAwayItemListCallback.onItemDeleted()
          }

          override fun onSubscribe(d: Disposable) {
          }

          override fun onError(e: Throwable) {
          }
        })
  }

}