package com.example.bd0631.goldseeker.throwAwayItemsList

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import com.example.bd0631.goldseeker.CustomViewModelFactory
import com.example.bd0631.goldseeker.R
import com.example.bd0631.goldseeker.additems.AddNewItemsActivity
import com.example.bd0631.goldseeker.base.BaseActivity
import com.example.bd0631.goldseeker.replaceFragmentInActivity
import javax.inject.Inject

class ThrowAwayItemListActivity: BaseActivity(), ThrowAwayItemListNavigator {

  override fun getLayoutId() = R.layout.activity_throw_away_items_list

  lateinit var viewModel: ThrowAwayItemListViewModel

  @Inject
  lateinit var customViewModelFactory: CustomViewModelFactory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    supportFragmentManager.findFragmentById(R.id.content_frame) ?:
        ThrowAwayItemListFragment.newInstance().also {
          replaceFragmentInActivity(it, R.id.content_frame)
        }
  }


  override fun onAddNewItemClicked() {
    val intent = Intent(this, AddNewItemsActivity::class.java)
    startActivityForResult(intent, AddNewItemsActivity.REQUEST_CODE)
  }

  override fun obtainViewModel(): ViewModel {
    viewModel = ViewModelProviders.of(this, customViewModelFactory)
        .get(ThrowAwayItemListViewModel::class.java)
    viewModel.setNavigator(this)
    return viewModel

  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    viewModel.loadThrowAwayItem()
  }
}