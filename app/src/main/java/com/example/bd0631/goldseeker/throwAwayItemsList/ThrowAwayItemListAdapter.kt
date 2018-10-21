package com.example.bd0631.goldseeker.throwAwayItemsList

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.bd0631.goldseeker.R
import com.example.bd0631.goldseeker.base.BaseRecyclerAdapter
import com.example.bd0631.goldseeker.database.PickUpLacationsRepo
import com.example.bd0631.goldseeker.database.PickUpLocation
import com.example.bd0631.goldseeker.utils.FileCreator

class ThrowAwayItemListAdapter(
    private var list: List<PickUpLocation>,
    private val pickUpLacationsRepo: PickUpLacationsRepo,
    private val context: Context?,
    private val listItemNavigator: ListItemNavigator
) : BaseRecyclerAdapter(), ThrowAwayItemListCallback {

  override fun getLayoutIdForPosition(position: Int) = R.layout.item_throw_away_item_list

  override fun getViewModel(position: Int) =
      ThrowAwayItemListItemViewModel(
          list[position],
          pickUpLacationsRepo,
          getImageBitmap(list[position].id),
          this)

  override fun getItemCount() = list.size

  fun updateData(list: List<PickUpLocation>) {
    this.list = list
    notifyDataSetChanged()
  }

  private fun getImageBitmap(id: Long): Bitmap? {
    val file = FileCreator().createImageFile(id, context)
    return if (file.exists()) BitmapFactory.decodeFile(file.absolutePath)
    else null
  }

  override fun onItemDeleted(pickUpLocation: PickUpLocation?) {
    list[0].Address = "OOOOPPAA"
    listItemNavigator.onItemDeleted(pickUpLocation)
    notifyDataSetChanged()
  }

  override fun onItemSelected(pickUpLocation: PickUpLocation?) {
    listItemNavigator.onItemSelected(pickUpLocation)
  }
}