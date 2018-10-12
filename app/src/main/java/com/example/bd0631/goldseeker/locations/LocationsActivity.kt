package com.example.bd0631.goldseeker.locations

import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.example.bd0631.goldseeker.R
import com.example.bd0631.goldseeker.replaceFragmentInActivity
import com.example.bd0631.goldseeker.throwAwayItemsList.ThrowAwayItemListActivity
import kotlinx.android.synthetic.main.activity_throw_away_items_list.*

class LocationsActivity : AppCompatActivity() {


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_locations)
    supportFragmentManager.findFragmentById(R.id.content_frame)
        ?: LocationsFragment.newInstance().also {
          replaceFragmentInActivity(it, R.id.content_frame)
        }
    setUpNavigationDrawer()
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

  /**
   * Manipulates the map once available.
   * This callback is triggered when the map is ready to be used.
   * This is where we can add markers or lines, add listeners or move the camera. In this case,
   * we just add a marker near Sydney, Australia.
   * If Google Play services is not installed on the device, the user will be prompted to install
   * it inside the SupportMapFragment. This method will only be triggered once the user has
   * installed Google Play services and returned to the app.
   */

}
