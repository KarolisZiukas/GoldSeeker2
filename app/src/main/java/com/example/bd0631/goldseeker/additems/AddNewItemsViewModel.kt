package com.example.bd0631.goldseeker.additems

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.databinding.ObservableField
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.util.Log
import com.example.bd0631.goldseeker.database.PickUpLacationsRepo
import com.example.bd0631.goldseeker.database.PickUpLocation
import com.example.bd0631.goldseeker.utils.LocationHelper
import com.example.karolis.logginhours.widgets.Generator
import com.google.firebase.firestore.FirebaseFirestore
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.ByteArrayOutputStream
import java.io.File
import javax.inject.Inject


class AddNewItemsViewModel @Inject constructor(
    val pickUpLocationsRepo: PickUpLacationsRepo) : ViewModel() {

  private lateinit var navigator: AddNewItemsNavigator
  private lateinit var context: Context

  val locationName = ObservableField<String>()
  val warehouseName = ObservableField<String>()
  val itemsList = ObservableField<String>()
  val itemImage = ObservableField<Bitmap>()
  var id: Long = 0

  fun setNavigator(addNewItemsNavigator: AddNewItemsNavigator) {
    id = Generator.generateId()
    this.navigator = addNewItemsNavigator
  }

  fun setContext(context: Context) {
    this.context = context
  }


  private fun saveThrowAwayItemRemote() {
    val firestore = FirebaseFirestore.getInstance()

    firestore.collection("users")
        .add(PickUpLocation(id,
            locationName.get(),
            warehouseName.get(),
            itemsList.get(),
            54.2142,
            24.43242
        ))
        .addOnSuccessListener {
          navigator.onNewPickUpItemSaved()
          Log.d("SAVED", "DocumentSnapshot added with ID: " + it.id)
        }
        .addOnFailureListener {
          Log.d("FAILED SAVING", "Not added" + it.localizedMessage)
        }
  }

  private fun saveThrowAwayItemsLocal() {

    val coordinates = LocationHelper().getCoordinates(locationName.get(), context)

    val stream = ByteArrayOutputStream()
    itemImage.get()?.compress(Bitmap.CompressFormat.PNG, 100, stream)
    Completable.fromAction {
      pickUpLocationsRepo
          .insertPickUpLocations(PickUpLocation(id,
              locationName.get(),
              warehouseName.get(),
              itemsList.get(),
              coordinates?.get(0)?.longitude,
              coordinates?.get(0)?.latitude
          )
          )
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
    saveThrowAwayItemRemote()
  }

  fun loadImageFromFile(id: Long, file: File?) {
    if (file!!.exists()) {
      itemImage.set(BitmapFactory.decodeFile(file.absolutePath))
    }
  }
}