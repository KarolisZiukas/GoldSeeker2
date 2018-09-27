package com.example.bd0631.goldseeker.additems

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
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

  fun obtainViewModel(): AddNewItemsViewModel {
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
    const val ADD_RESULT_OK = RESULT_FIRST_USER + 1
  }
}