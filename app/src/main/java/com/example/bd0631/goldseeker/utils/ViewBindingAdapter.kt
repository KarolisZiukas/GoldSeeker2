package com.example.bd0631.goldseeker.utils

import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.databinding.ObservableField
import android.graphics.Bitmap
import android.widget.ImageView

object ViewBindingAdapter {

  @JvmStatic
  @BindingAdapter("bind:imageBitmap")
  fun loadImage(imageView: ImageView, bitmap: ObservableField<Bitmap>?) {
    imageView.setImageBitmap(bitmap?.get())
  }
}