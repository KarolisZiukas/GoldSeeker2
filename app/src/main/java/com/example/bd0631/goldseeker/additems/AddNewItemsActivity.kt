package com.example.bd0631.goldseeker.additems

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import com.example.bd0631.goldseeker.CustomViewModelFactory
import com.example.bd0631.goldseeker.R
import com.example.bd0631.goldseeker.base.BaseActivity
import com.example.bd0631.goldseeker.replaceFragmentInActivity
import javax.inject.Inject

class AddNewItemsActivity : BaseActivity(), AddNewItemsNavigator {

  @Inject
  lateinit var customViewModelFactory: CustomViewModelFactory

  override fun getLayoutId() = R.layout.activity_add_new_items
  private lateinit var viewModel: AddNewItemsViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    supportFragmentManager.findFragmentById(R.id.content_frame)
        ?: AddNewItemsFragment.newInstance().also {
          replaceFragmentInActivity(it, R.id.content_frame)
        }
  }

  override fun obtainViewModel(): AddNewItemsViewModel {
    viewModel = ViewModelProviders.of(this, customViewModelFactory)
        .get(AddNewItemsViewModel::class.java)
    viewModel.setNavigator(this)
    return viewModel
  }

  override fun onNewPickUpItemSaved() {
    setResult(ADD_RESULT_OK)
    finish()
  }

  companion object {
    const val REQUEST_CODE = 1

    const val REQUEST_IMAGE_CAPTURE = 1

    const val ADD_RESULT_OK = RESULT_FIRST_USER + 1
  }

  override fun onAddPictureClicked() {
    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
      takePictureIntent.resolveActivity(packageManager)?.also {
        startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
      }
    }
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
      val imageBitmap = data?.extras?.get("data") as Bitmap
      viewModel.itemImage.set(imageBitmap)
    }
  }
}