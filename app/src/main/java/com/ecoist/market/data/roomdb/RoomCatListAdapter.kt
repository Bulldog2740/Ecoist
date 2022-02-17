package com.ecoist.market.data.roomdb

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ecoist.market.R

/**
 *Created by Yehor Kudimov on 4/27/2021.
 */
class RoomCatListAdapter(private var listener: Listener) :
    ListAdapter<CategoryModel, RoomCatListAdapter.RoomCatVh>(diff) {
    companion object {
        val diff = object : DiffUtil.ItemCallback<CategoryModel>() {
            override fun areItemsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
                return oldItem.id == newItem.id && oldItem.idParent == newItem.idParent
            }

            override fun areContentsTheSame(
                oldItem: CategoryModel,
                newItem: CategoryModel
            ): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

    class RoomCatVh(val view: View) : RecyclerView.ViewHolder(view) {

        private var tvCategoryName: TextView? = view.findViewById(R.id.tvCategoryName)
        private var ivCategoryLogo: ImageView? = view.findViewById(R.id.lev)

        fun bind(category: CategoryModel, listener: Listener) {

            ivCategoryLogo?.context?.let {
                Glide.with(it).load(category.image).into(ivCategoryLogo!!)
            }
            tvCategoryName?.text = category.name
            itemView.setOnClickListener {
                listener.onClick(category)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomCatVh {
        var inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_category_list_item, parent, false)
        return RoomCatVh(inflater.rootView)
    }

    override fun onBindViewHolder(holder: RoomCatVh, position: Int) {
        val item = getItem(position)
        if (item != null) holder.bind(item, listener)
    }

    interface Listener {
        fun onClick(category: CategoryModel)
    }
}