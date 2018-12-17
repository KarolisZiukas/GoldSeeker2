package com.example.bd0631.goldseeker.details

import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bd0631.goldseeker.R
import com.example.bd0631.goldseeker.database.ROOM.PickUpLocation
import com.example.bd0631.goldseeker.databinding.FragmentItemDetailsBinding
import com.example.bd0631.goldseeker.utils.FileCreator

class DetailsFragment: Fragment() {

  lateinit var databinding: FragmentItemDetailsBinding
  lateinit var viewModel: DetailsViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    super.onCreateView(inflater, container, savedInstanceState)
    databinding = DataBindingUtil.inflate(inflater,
        R.layout.fragment_item_details,
        container,
        false)
    return databinding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    val pickUpLocation = arguments?.getSerializable("LOCATIONS") as PickUpLocation
    viewModel = (activity as DetailsActivity).obtainViewModel()

    viewModel.throwAwayItem.value = pickUpLocation
    viewModel.imageFile= getImageBitmap(pickUpLocation.id)
    databinding.viewModel = viewModel
  }

  companion object {
    fun newInstance(pickUpLocation: PickUpLocation): DetailsFragment {
      return DetailsFragment()
          .apply {
            this.arguments = Bundle().apply {
              putSerializable("LOCATIONS", pickUpLocation)
            }
          }
    }
  }


  private fun getImageBitmap(id: Long): Bitmap? {
    val file = FileCreator().createImageFile(id, context)
    return if (file.exists()) BitmapFactory.decodeFile(file.absolutePath)
    else null
  }

}
