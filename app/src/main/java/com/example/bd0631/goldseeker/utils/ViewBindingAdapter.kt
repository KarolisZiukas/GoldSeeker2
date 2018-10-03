package com.example.bd0631.goldseeker.utils

import android.databinding.BindingAdapter
import android.graphics.Bitmap
import android.widget.ImageView

object ViewBindingAdapter {

  @JvmStatic
  @BindingAdapter("bind:imageBitmap")
  fun loadImage(imageView: ImageView, bitmap: Bitmap?) {
    imageView.setImageBitmap(bitmap)
  }
}