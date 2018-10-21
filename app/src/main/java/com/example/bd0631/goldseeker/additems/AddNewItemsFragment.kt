package com.example.bd0631.goldseeker.additems

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bd0631.goldseeker.R
import com.example.bd0631.goldseeker.databinding.FragmentAddNewItemsBinding
import com.example.bd0631.goldseeker.utils.LocationHelper
import kotlinx.android.synthetic.main.fragment_add_new_items.*

class AddNewItemsFragment: Fragment(), View.OnClickListener {

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
  }

  companion object {
    fun newInstance(): AddNewItemsFragment {
      return AddNewItemsFragment()
    }
  }

  override fun onClick(p0: View?) {
    viewModel.saveThrowAwayItems(
        LocationHelper().getCoordinates(viewModel.locationName.get(), context!!)
    )
  }


}