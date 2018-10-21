package com.example.bd0631.goldseeker.locations

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.example.bd0631.goldseeker.CustomViewModelFactory
import com.example.bd0631.goldseeker.R
import com.example.bd0631.goldseeker.base.BaseActivity
import com.example.bd0631.goldseeker.replaceFragmentInActivity
import com.example.bd0631.goldseeker.throwAwayItemsList.ThrowAwayItemListActivity
import kotlinx.android.synthetic.main.activity_throw_away_items_list.*
import javax.inject.Inject

class LocationsActivity : BaseActivity() {

  @Inject
  lateinit var customViewModelFactory: CustomViewModelFactory

  override fun getLayoutId() = R.layout.activity_locations
  private lateinit var viewModel: LocationsViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    supportFragmentManager.findFragmentById(R.id.content_frame)
        ?: LocationsFragment.newInstance().also {
          replaceFragmentInActivity(it, R.id.content_frame)
        }
    setUpNavigationDrawer()
    val toolbar: Toolbar = findViewById(R.id.toolbar)
    setSupportActionBar(toolbar)
    val actionbar: ActionBar? = supportActionBar
    actionbar?.apply {
      setDisplayHomeAsUpEnabled(true)
      setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
    }
  }

  private fun setUpNavigationDrawer() {
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

  override fun obtainViewModel(): LocationsViewModel {
    viewModel = ViewModelProviders.of(this, customViewModelFactory)
        .get(LocationsViewModel::class.java)
    return viewModel
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    return when (item.itemId) {
      android.R.id.home -> {
        drawer_layout.openDrawer(GravityCompat.START)
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }
}
