package com.example.bd0631.goldseeker.details

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.example.bd0631.goldseeker.CustomViewModelFactory
import com.example.bd0631.goldseeker.R
import com.example.bd0631.goldseeker.additems.AddNewItemsActivity.Companion.ADD_RESULT_OK
import com.example.bd0631.goldseeker.base.BaseActivity
import com.example.bd0631.goldseeker.database.ROOM.PickUpLocation
import com.example.bd0631.goldseeker.replaceFragmentInActivity
import javax.inject.Inject

class DetailsActivity: BaseActivity(), DetailsActionListener {

  @Inject
  lateinit var customViewModelFactory: CustomViewModelFactory

  private lateinit var viewModel: DetailsViewModel


  override fun getLayoutId() = R.layout.activity_details

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val pickUpLocation = intent.getSerializableExtra("Location") as PickUpLocation
    supportFragmentManager.findFragmentById(R.id.content_frame)
        ?: DetailsFragment.newInstance(pickUpLocation).also {
          replaceFragmentInActivity(it, R.id.content_frame)
        }

    val toolbar: Toolbar = findViewById(R.id.toolbar)
    setSupportActionBar(toolbar)
  }

  override fun obtainViewModel(): DetailsViewModel {
    viewModel = ViewModelProviders.of(this, customViewModelFactory)
        .get(DetailsViewModel::class.java)
    viewModel.setNavigator(this)
    return viewModel
  }

  override fun onItemDeleted() {
    setResult(ADD_RESULT_OK)
    finish()
  }
}