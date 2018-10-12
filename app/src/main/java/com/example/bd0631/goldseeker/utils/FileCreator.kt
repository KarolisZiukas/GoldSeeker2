package com.example.bd0631.goldseeker.utils

import android.content.Context
import android.os.Environment
import java.io.File
import java.io.IOException

class FileCreator {

  @Throws(IOException::class)
  fun createImageFile(id: Long, activity: Context?): File {
    return File(activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
        "$id.jpg")
  }

}