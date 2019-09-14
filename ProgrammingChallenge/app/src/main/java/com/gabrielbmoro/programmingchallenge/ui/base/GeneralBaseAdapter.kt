package com.gabrielbmoro.programmingchallenge.ui.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.gabrielbmoro.programmingchallenge.BR

abstract class GeneralBaseAdapter<T> : RecyclerView.Adapter<GeneralBaseAdapter.GeneralViewHolder>() {

    var elements: ArrayList<T>? = null

    @LayoutRes
    abstract fun layoutResourceId(): Int

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): GeneralViewHolder {
        val view = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(viewGroup.context), layoutResourceId(), viewGroup, false).root
        return GeneralViewHolder(view)
    }

    override fun onBindViewHolder(generalViewHolder: GeneralViewHolder, i: Int) {
        DataBindingUtil.getBinding<ViewDataBinding>(generalViewHolder.itemView)?.apply {
            elements?.get(i)?.let{
                setVariable(BR.viewModel, it)
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return elements?.size ?: 0
    }

    open class GeneralViewHolder(view : View) : RecyclerView.ViewHolder(view)

}

