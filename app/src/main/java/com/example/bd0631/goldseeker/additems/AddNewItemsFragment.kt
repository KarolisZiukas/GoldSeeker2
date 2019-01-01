package com.example.bd0631.goldseeker.additems

import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bd0631.goldseeker.R
import com.example.bd0631.goldseeker.databinding.FragmentAddNewItemsBinding
import com.example.bd0631.goldseeker.utils.LocationHelper
import kotlinx.android.synthetic.main.fragment_add_new_items.*
import java.io.IOException

class AddNewItemsFragment: Fragment(), View.OnClickListener {

  private val TAG = "TfLiteCameraDemo"

  private val HANDLE_THREAD_NAME = "CameraBackground"

  private val lock = Any()
  private var runClassifier = false
  private var classifier: ImageClassifier? = null

  /** An additional thread for running tasks that shouldn't block the UI.  */
  private var backgroundThread: HandlerThread? = null

  /** A [Handler] for running tasks in the background.  */
  private var backgroundHandler: Handler? = null

  lateinit var databinding: FragmentAddNewItemsBinding

  private lateinit var viewModel: AddNewItemsViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    super.onCreateView(inflater, container, savedInstanceState)
    databinding = DataBindingUtil.inflate(inflater,
        R.layout.fragment_add_new_items,
        container,
        false)
    return databinding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = (activity as AddNewItemsActivity).obtainViewModel()
    databinding.viewModel = viewModel
    bt_add_items.setOnClickListener(this)
    try {
      classifier = ImageClassifier(activity as AddNewItemsActivity)
    } catch (e: IOException) {
      Log.e(TAG, "Failed to initialize an image classifier.")
    }
    startBackgroundThread()
  }

  override fun onClick(p0: View?) {
    viewModel.saveThrowAwayItems(
        LocationHelper().getCoordinates(viewModel.locationName.get(), context!!)
    )
  }

  companion object {
    fun newInstance(): AddNewItemsFragment {
      return AddNewItemsFragment()
    }
  }

  private fun showToast(text: String) {
    val activity = activity
    activity?.runOnUiThread { tv_tensor.text = text }
  }

  override fun onResume() {
    super.onResume()
    startBackgroundThread()
  }

  override fun onPause() {
    stopBackgroundThread()
    super.onPause()
  }

  override fun onDestroy() {
    classifier?.close()
    super.onDestroy()
  }

  private fun startBackgroundThread() {
    backgroundThread = HandlerThread(HANDLE_THREAD_NAME)
    backgroundThread?.start()
    backgroundHandler = Handler(backgroundThread?.looper)
    synchronized(lock) {
      runClassifier = true
    }
    backgroundHandler?.post(periodicClassify)
  }

  /** Stops the background thread and its [Handler].  */
  private fun stopBackgroundThread() {
    backgroundThread?.quitSafely()
    try {
      backgroundThread?.join()
//      backgroundThread = null
//      backgroundHandler = null
      synchronized(lock) {
        runClassifier = false
      }
    } catch (e: InterruptedException) {
      e.printStackTrace()
    }

  }

  private val periodicClassify = object : Runnable {
    override fun run() {
      synchronized(lock) {
        if (runClassifier) {
          classifyFrame()
        }
      }
      backgroundHandler?.post(this)
    }
  }

  private fun classifyFrame() {
    if (classifier == null || activity == null) {
      showToast("Uninitialized Classifier or invalid context.")
      return
    }
    //FIX THREADING
    viewModel.itemImage.observe(this, Observer {
      showToast(classifier?.classifyFrame(it)!!)
    })
  }
}