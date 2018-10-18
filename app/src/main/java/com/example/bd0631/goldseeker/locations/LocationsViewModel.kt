package com.example.bd0631.goldseeker.locations

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.example.bd0631.goldseeker.database.PickUpLacationsRepo
import com.example.bd0631.goldseeker.database.PickUpLocation
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class LocationsViewModel @Inject constructor(
    private val pickUpLacationsRepo: PickUpLacationsRepo
) : ViewModel() {

  var throwAwayItemsList: MutableLiveData<List<PickUpLocation>>? = null

  fun getThrowAwayItem(): LiveData<List<PickUpLocation>>? {
    if (throwAwayItemsList == null) {
      throwAwayItemsList = MutableLiveData()
      loadThrowAwayItem()
    }
    return throwAwayItemsList
  }

  private fun loadThrowAwayItemsFromRemote() {
    FirebaseFirestore.getInstance()
        .collection("users")
        .get()
        .addOnSuccessListener {
          if (!it.isEmpty) {
            throwAwayItemsList?.postValue(it.toObjects(PickUpLocation::class.java))
          } else {
          }
        }
  }

  fun loadThrowAwayItem() {
    pickUpLacationsRepo.getAllPickUpLocations()
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object : Observer<List<PickUpLocation>> {

          override fun onComplete() {

          }

          override fun onSubscribe(d: Disposable) {
          }

          override fun onNext(t: List<PickUpLocation>) {
            if(t.isEmpty()) {
              loadThrowAwayItemsFromRemote()
            } else {
              throwAwayItemsList?.value = t
            }
          }

          override fun onError(e: Throwable) {
          }
        })
  }


}



