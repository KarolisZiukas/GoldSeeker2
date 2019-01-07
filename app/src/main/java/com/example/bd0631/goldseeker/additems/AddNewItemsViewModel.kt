package com.example.bd0631.goldseeker.additems

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.location.Address
import android.util.Log
import com.example.bd0631.goldseeker.database.PickUpLacationsRepo
import com.example.bd0631.goldseeker.database.ROOM.PickUpLocation
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

  val locationName = ObservableField<String>()
  val phoneNumber = ObservableField<String>()
  val itemsList = ObservableField<String>()
  val itemImage = ObservableField<Bitmap>()
  val isLoading = MutableLiveData<Boolean>()
  var id: Long = 0

  fun setNavigator(addNewItemsNavigator: AddNewItemsNavigator) {
    id = Generator.generateId()
    this.navigator = addNewItemsNavigator
  }

  private fun saveThrowAwayItemRemote(coordinates: List<Address>?) {
    val firestore = FirebaseFirestore.getInstance()
    isLoading.value = true
    firestore.collection("locations")
        .document(id.toString())
        .set(
            PickUpLocation(id,
            locationName.get(),
            phoneNumber.get().toString(),
            itemsList.get(),
            coordinates?.get(0)?.longitude,
            coordinates?.get(0)?.latitude
        )
        )
        .addOnSuccessListener {
          isLoading.value = false
          navigator.onNewPickUpItemSaved()
          Log.d("SAVED", "DocumentSnapshot added with ID: ")
        }
        .addOnFailureListener {
          isLoading.value = false
          Log.d("FAILED SAVING", "Not added" + it.localizedMessage)
        }
  }

  private fun saveThrowAwayItemsLocal(coordinates: List<Address>?, item: String) {
    val stream = ByteArrayOutputStream()
    itemImage.get()?.compress(Bitmap.CompressFormat.PNG, 100, stream)
//    itemImage.value?.compress(Bitmap.CompressFormat.PNG, 100, stream)
    Completable.fromAction {
      pickUpLocationsRepo
          .insertPickUpLocations(
              PickUpLocation(id,
              item,
              phoneNumber.get().toString(),
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
            isLoading.value = false
            navigator.onNewPickUpItemSaved()
          }

          override fun onSubscribe(d: Disposable) {
            isLoading.value = true
          }

          override fun onError(e: Throwable) {
            isLoading.value = false
            Log.d("SAVING ERROR", e.message)
          }
        })
  }

  fun addPicture() {
    navigator.onAddPictureClicked()
  }

  fun saveThrowAwayItems(coordinates: List<Address>?, item: String) {
    isLoading.value = true
    saveThrowAwayItemsLocal(coordinates, item)
    saveThrowAwayItemRemote(coordinates)
  }

  fun loadImageFromFile(id: Long, file: File?) {
    if (file!!.exists()) {
      itemImage.set(BitmapFactory.decodeFile(file.absolutePath))
//      itemImage.value = BitmapFactory.decodeFile(file.absolutePath)
    }
  }
}