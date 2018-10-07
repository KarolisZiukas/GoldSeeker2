package com.example.bd0631.goldseeker.throwAwayItemsList

import com.example.bd0631.goldseeker.R
import com.example.bd0631.goldseeker.base.BaseRecyclerAdapter
import com.example.bd0631.goldseeker.database.PickUpLacationsRepo
import com.example.bd0631.goldseeker.database.PickUpLocation

class ThrowAwayItemListAdapter(
    private var list: List<PickUpLocation>,
    private val pickUpLacationsRepo: PickUpLacationsRepo
): BaseRecyclerAdapter(), ThrowAwayItemListCallback {

  override fun getLayoutIdForPosition(position: Int) = R.layout.item_throw_away_item_list

  override fun getViewModel(position: Int) = ThrowAwayItemListItemViewModel(list[position], pickUpLacationsRepo, this)

  override fun getItemCount() = list.size

  fun updateData(list: List<PickUpLocation>) {
    this.list = list
    notifyDataSetChanged()
  }

  override fun onItemDeleted() {
    notifyDataSetChanged()
  }
}