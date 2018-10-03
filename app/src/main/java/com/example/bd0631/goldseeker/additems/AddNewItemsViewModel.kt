package com.example.bd0631.goldseeker.additems

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.graphics.Bitmap
import android.util.Log
import com.example.bd0631.goldseeker.database.PickUpLacationsRepo
import com.example.bd0631.goldseeker.database.PickUpLocation
import com.example.karolis.logginhours.widgets.Generator
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class AddNewItemsViewModel @Inject constructor(
    val pickUpLocationsRepo: PickUpLacationsRepo) : ViewModel() {

  private lateinit var navigator: AddNewItemsNavigator

  val locationName = ObservableField<String>()
  val warehouseName = ObservableField<String>()
  val itemsList = ObservableField<String>()
  val itemImage = ObservableField<Bitmap>()

  fun setNavigator(addNewItemsNavigator: AddNewItemsNavigator) {
    this.navigator = addNewItemsNavigator
  }

  //Todo Fix remote
//  private fun saveThrowAwayItemRemote() {
//    val user = HashMap<String, Any>()
//    user["first"] = "Ada"
//    user["last"] = "Lovelace"
//    user["born"] = 1815
//    val firestore = FirebaseFirestore.getInstance()
//
//    firestore.collection("users")
//        .add(PickUpLocation(Generator.generateId()))
//        .addOnSuccessListener {
//          Log.d("SAVED", "DocumentSnapshot added with ID: " + it.id)
//        }
//        .addOnFailureListener {
//          Log.d("FAILED SAVING", "Not added" + it.localizedMessage)
//        }
//  }

  private fun saveThrowAwayItemsLocal() {
    Completable.fromAction {
      pickUpLocationsRepo
          .insertPickUpLocations(PickUpLocation(Generator.generateId(),
              locationName.get(),
              warehouseName.get(),
              itemsList.get()
          ))
    }
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.newThread())
        .subscribe(object : CompletableObserver {
          override fun onComplete() {
            navigator.onNewPickUpItemSaved()
          }

          override fun onSubscribe(d: Disposable) {
          }

          override fun onError(e: Throwable) {
            Log.d("SAVING ERROR", e.message)
          }
        })
  }

  fun addPicture() {
    navigator.onAddPictureClicked()
  }

  fun saveThrowAwayItems() {
    saveThrowAwayItemsLocal()
//    saveThrowAwayItemRemote()
  }
}