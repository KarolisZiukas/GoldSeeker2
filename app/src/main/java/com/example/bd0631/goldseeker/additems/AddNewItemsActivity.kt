package com.example.bd0631.goldseeker.additems

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.FileProvider
import com.example.bd0631.goldseeker.CustomViewModelFactory
import com.example.bd0631.goldseeker.R
import com.example.bd0631.goldseeker.base.BaseActivity
import com.example.bd0631.goldseeker.replaceFragmentInActivity
import com.example.bd0631.goldseeker.utils.FileCreator
import com.example.bd0631.goldseeker.utils.LocationHelper
import com.example.karolis.logginhours.widgets.Generator
import java.io.File
import java.io.IOException
import javax.inject.Inject

class AddNewItemsActivity : BaseActivity(), AddNewItemsNavigator {

  @Inject
  lateinit var customViewModelFactory: CustomViewModelFactory

  override fun getLayoutId() = R.layout.activity_add_new_items
  private lateinit var viewModel: AddNewItemsViewModel
  val REQUEST_TAKE_PHOTO = 1
  var photoFile: File? = null
  var id: Long = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    id = Generator.generateId()
    supportFragmentManager.findFragmentById(R.id.content_frame)
        ?: AddNewItemsFragment.newInstance().also {
          replaceFragmentInActivity(it, R.id.content_frame)
        }
  }

  override fun obtainViewModel(): AddNewItemsViewModel {
    viewModel = ViewModelProviders.of(this, customViewModelFactory)
        .get(AddNewItemsViewModel::class.java)
    viewModel.setNavigator(this)
    viewModel.setContext(this)

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
    dispatchTakePictureIntent()
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    viewModel.loadImageFromFile(id, photoFile)
  }

  private fun dispatchTakePictureIntent() {
    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
      takePictureIntent.resolveActivity(packageManager)?.also {
        photoFile = try {
          FileCreator().createImageFile(viewModel.id, this)
        } catch (ex: IOException) {
          null
        }
        photoFile?.also {
          val photoURI: Uri = FileProvider.getUriForFile(
              this,
              "com.example.android.fileprovider",
              it
          )
          takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
          startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO)
        }
      }
    }
  }



}