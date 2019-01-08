package com.example.bd0631.goldseeker.database.ROOM

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import java.io.Serializable

@Entity(tableName = "pickUpLocations")
data class PickUpLocation(
    @PrimaryKey @NonNull var id: Long = 0,
    var Address: String? = null,
    var phoneNumber: String? = null,
    var itemName: String? = null,
    var itemDescription: String? = null,
    var latitude: Double? = 0.0,
    var longitude: Double? = 0.0
): Serializable

