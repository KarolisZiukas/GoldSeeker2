package com.example.bd0631.goldseeker.database

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "pickUpLocations")
data class PickUpLocation(
    @PrimaryKey @NonNull var id: Long = 0,
    var Address: String? = null,
    var warehouseName: String? = null,
    var itemsList: String? = null,
    var latitude: Double? = 0.0,
    var longitude: Double? = 0.0
)

