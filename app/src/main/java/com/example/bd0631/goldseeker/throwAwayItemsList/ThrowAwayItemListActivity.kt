package com.example.bd0631.goldseeker.throwAwayItemsList

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import com.example.bd0631.goldseeker.CustomViewModelFactory
import com.example.bd0631.goldseeker.R
import com.example.bd0631.goldseeker.additems.AddNewItemsActivity
import com.example.bd0631.goldseeker.base.BaseActivity
import com.example.bd0631.goldseeker.locations.LocationsActivity
import com.example.bd0631.goldseeker.locations.LocationsFragment
import com.example.bd0631.goldseeker.replaceFragmentInActivity
import kotlinx.android.synthetic.main.activity_throw_away_items_list.*
import javax.inject.Inject

class ThrowAwayItemListActivity : BaseActivity(), ThrowAwayItemListNavigator {

  override fun getLayoutId() = R.layout.activity_throw_away_items_list

  lateinit var viewModel: ThrowAwayItemListViewModel

  @Inject
  lateinit var customViewModelFactory: CustomViewModelFactory

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    supportFragmentManager.findFragmentById(R.id.content_frame)
        ?: ThrowAwayItemListFragment.newInstance().also {
          replaceFragmentInActivity(it, R.id.content_frame)
        }
    setUpNavigationDrawer()
  }

  private fun setUpNavigationDrawer() {
    nav_view.menu.getItem(0)
        .isChecked = true
    nav_view.setNavigationItemSelectedListener {
      when (it.itemId) {
        R.id.item_throw_away_items_list -> {
          val intent = Intent(this, ThrowAwayItemListActivity::class.java)
          startActivity(intent)
        }
        R.id.item_map -> {
          val intent = Intent(this, LocationsActivity::class.java)
          startActivity(intent)
        }
      }
      it.isChecked = true
      drawer_layout.closeDrawer(GravityCompat.START)
      true
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