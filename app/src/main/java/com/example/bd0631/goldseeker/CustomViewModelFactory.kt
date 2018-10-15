package com.example.bd0631.goldseeker

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.bd0631.goldseeker.details.DetailsViewModel
import com.example.bd0631.goldseeker.additems.AddNewItemsViewModel
import com.example.bd0631.goldseeker.database.PickUpLacationsRepo
import com.example.bd0631.goldseeker.locations.LocationsViewModel
import com.example.bd0631.goldseeker.throwAwayItemsList.ThrowAwayItemListViewModel
import javax.inject.Inject

class CustomViewModelFactory @Inject constructor(val pickUpLacationsRepo: PickUpLacationsRepo):
    ViewModelProvider.Factory {


  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(AddNewItemsViewModel::class.java)) {
      return AddNewItemsViewModel(pickUpLacationsRepo) as T
    } else if (modelClass.isAssignableFrom(ThrowAwayItemListViewModel::class.java)) {
      return ThrowAwayItemListViewModel(pickUpLacationsRepo) as T
    } else if (modelClass.isAssignableFrom(LocationsViewModel::class.java)) {
      return LocationsViewModel(pickUpLacationsRepo) as T
    } else if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
      return DetailsViewModel(pickUpLacationsRepo) as T
    }


    throw IllegalArgumentException("Unknown class name")
  }
}