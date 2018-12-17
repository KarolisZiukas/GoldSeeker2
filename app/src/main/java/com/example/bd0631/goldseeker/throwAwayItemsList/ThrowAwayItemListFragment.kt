package com.example.bd0631.goldseeker.throwAwayItemsList

import android.arch.lifecycle.Observer
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bd0631.goldseeker.details.DetailsActivity
import com.example.bd0631.goldseeker.R
import com.example.bd0631.goldseeker.additems.AddNewItemsActivity
import com.example.bd0631.goldseeker.database.ROOM.PickUpLocation
import com.example.bd0631.goldseeker.databinding.FragmentThrowAwayItemsListBinding

class ThrowAwayItemListFragment : Fragment(), ListItemNavigator {

  private lateinit var databinding: FragmentThrowAwayItemsListBinding

  private lateinit var viewModel: ThrowAwayItemListViewModel

  lateinit var recyclerView: RecyclerView

  lateinit var adapter: ThrowAwayItemListAdapter

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    super.onCreateView(inflater, container, savedInstanceState)
    databinding = DataBindingUtil.inflate(
        inflater,
        R.layout.fragment_throw_away_items_list,
        container,
        false)

    return databinding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = (activity as ThrowAwayItemListActivity).obtainViewModel() as ThrowAwayItemListViewModel
    databinding.viewModel = viewModel
    initRecyclerView()
  }

  private fun initRecyclerView() {
    recyclerView = databinding.rvThrowAwayItemsList
    recyclerView.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
    recyclerView.setHasFixedSize(true)
    adapter = ThrowAwayItemListAdapter(ArrayList(), viewModel.pickUpLacationsRepo, context, this)
    recyclerView.adapter = adapter
    viewModel.getThrowAwayItem()?.observe(this, Observer<List<PickUpLocation>> {
      adapter.updateData(it!!)
      adapter.notifyDataSetChanged()
    })
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    viewModel.loadThrowAwayItem()
    adapter.notifyDataSetChanged()
//    viewModel.getThrowAwayItem()?.observe(this, Observer<List<PickUpLocation>> {
//      adapter.updateData(it!!)
//      adapter.notifyDataSetChanged()
//    })
  }

  override fun onItemSelected(pickUpLocation: PickUpLocation?) {
    val intent = Intent(context, DetailsActivity::class.java)
    intent.putExtra("Location", pickUpLocation)
    startActivityForResult(intent, AddNewItemsActivity.REQUEST_CODE)
  }

  override fun onItemDeleted(pickUpLocation: PickUpLocation?) {
    viewModel.getThrowAwayItem()?.observe(this, Observer<List<PickUpLocation>> {
      adapter.updateData(it!!)
    })
  }

  companion object {

    fun newInstance(): ThrowAwayItemListFragment {
      return ThrowAwayItemListFragment()
    }

  }
}