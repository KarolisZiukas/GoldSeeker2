package com.example.bd0631.goldseeker.utils

class StringHelper {

  companion object {

    fun fromStringSeparatedByCommasToArrayList(string: String?): List<String>? {
      return string?.split(",")?.map { it.trim() }
    }

  }


}