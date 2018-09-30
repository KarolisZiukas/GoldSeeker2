package com.example.bd0631.goldseeker.base


import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.bd0631.goldseeker.BR

abstract class BaseRecyclerAdapter : RecyclerView.Adapter<BaseRecyclerAdapter.RecyclerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            RecyclerViewHolder(DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(parent.context), viewType, parent, false))

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        getViewModel(position)
                ?.let {
                    val bindingSuccess = holder.binding.setVariable(BR.viewModel, it)
                    if (!bindingSuccess) {
                        throw IllegalStateException("Binding ${holder.binding} viewModel variable name should be 'viewModel'")
                    }
                }
    }

    override fun getItemViewType(position: Int) = getLayoutIdForPosition(position)

    abstract fun getLayoutIdForPosition(position: Int): Int

    abstract fun getViewModel(position: Int): Any?

    open class RecyclerViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

}