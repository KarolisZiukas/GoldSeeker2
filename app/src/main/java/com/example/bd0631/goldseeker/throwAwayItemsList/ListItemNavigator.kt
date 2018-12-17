package com.example.bd0631.goldseeker.throwAwayItemsList

import com.example.bd0631.goldseeker.database.ROOM.PickUpLocation

interface ListItemNavigator {

  fun onItemSelected(pickUpLocation: PickUpLocation?)

  fun onItemDeleted(pickUpLocation: PickUpLocation?)

}
