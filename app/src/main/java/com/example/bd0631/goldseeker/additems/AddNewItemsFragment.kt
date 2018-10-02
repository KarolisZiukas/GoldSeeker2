package com.example.bd0631.goldseeker.additems

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bd0631.goldseeker.R
import com.example.bd0631.goldseeker.databinding.FragmentAddNewItemsBinding

class AddNewItemsFragment: Fragment(){

  lateinit var databinding: FragmentAddNewItemsBinding

  private lateinit var viewModel: AddNewItemsViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    super.onCreateView(inflater, container, savedInstanceState)
    databinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_new_items, container, false)
    return databinding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = (activity as AddNewItemsActivity).obtainViewModel()
    databinding.viewModel = viewModel

  }

  companion object {
    fun newInstance(): AddNewItemsFragment {
        return AddNewItemsFragment()
    }
  }
}