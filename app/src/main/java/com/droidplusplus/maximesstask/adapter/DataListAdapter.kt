package com.droidplusplus.maximesstask.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.droidplusplus.maximesstask.R
import com.droidplusplus.maximesstask.data.entity.DataItem
import com.droidplusplus.maximesstask.utils.Constants
import kotlinx.android.synthetic.main.row_item.view.*

class DataListAdapter :
    ListAdapter<DataItem, DataListAdapter.MViewHolder>(MDiffUtilCallback()) {

    var mlistener: (actionType: Int, item: DataItem) -> Unit = { _,  _ -> Unit }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.row_item, parent, false)
    )

    override fun onBindViewHolder(holder: MViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.apply {
            nameTv.text = item.name
            salaryTv.text = item.salary.toString()

            //Item click action
            editIV.setOnClickListener {
                mlistener.invoke(Constants.EDIT_ACTION, item)
            }

            //Item click action
            deleteIV.setOnClickListener {
                mlistener.invoke(Constants.DELETE_ACTION, item)
            }

            //Item click action
            itemCB.setOnClickListener {
                mlistener.invoke(Constants.SELECT_ACTION, item)
            }
        }
    }

    class MViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private class MDiffUtilCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem) =
            oldItem == newItem
    }
}