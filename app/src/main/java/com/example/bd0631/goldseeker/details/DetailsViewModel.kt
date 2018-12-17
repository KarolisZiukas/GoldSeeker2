package com.example.bd0631.goldseeker.details

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.graphics.Bitmap
import com.example.bd0631.goldseeker.database.PickUpLacationsRepo
import com.example.bd0631.goldseeker.database.ROOM.PickUpLocation
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val pickUpLacationsRepo: PickUpLacationsRepo
): ViewModel() {


  var imageFile: Bitmap? = null
  var throwAwayItem = MutableLiveData<PickUpLocation>()
  var isLoading = MutableLiveData<Boolean>()

  private lateinit var listener: DetailsActionListener


  fun getThrowAwayItem(pickUpLocation: PickUpLocation): LiveData<PickUpLocation>? {
    if (throwAwayItem == null) {
      throwAwayItem = MutableLiveData()
      loadThrowAwayItem(pickUpLocation)
    }
    return throwAwayItem
  }

  fun loadThrowAwayItem(pickUpLocation: PickUpLocation) {
    isLoading.value = true
    pickUpLacationsRepo.getSinglePickUpLocation(pickUpLocation.id)
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : Observer<PickUpLocation> {

          override fun onComplete() {
            isLoading.value = false
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

  fun removeItemLocal() {
    Completable.fromAction {
      pickUpLacationsRepo.removePickUpLocation(throwAwayItem?.value?.id)
    }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.newThread())
        .subscribe(object : CompletableObserver {
          override fun onComplete() {
          }

          override fun onSubscribe(d: Disposable) {
          }

          override fun onError(e: Throwable) {
          }
        })
  }

  fun removeItem() {
    removeItemLocal()
    removeItemFromRemote()
  }

  fun removeItemFromRemote() {
    FirebaseFirestore.getInstance()
        .collection("locations")
        .document(throwAwayItem.value?.id.toString())
        .delete()
        .addOnSuccessListener {
          listener.onItemDeleted()
        }
  }

  fun setNavigator(detailsActionListener: DetailsActionListener) {
    this.listener = detailsActionListener
  }


}