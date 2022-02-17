package com.ecoist.market.presentation.category.adapter

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
import com.ecoist.market.data.model.Category


class CategoryListAdapter(
    private var listener: Listener
) : ListAdapter<Category, CategoryListAdapter.CategoryVH>(diff) {

    companion object {
        val diff = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.id == newItem.id && oldItem.idParent == newItem.idParent
            }

            override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_category_list_item, parent, false)
        return CategoryVH(itemView)
    }


    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        val item = getItem(position)
        if (item != null) holder.bind(item, listener)
    }


    class CategoryVH(view: View) : RecyclerView.ViewHolder(view) {

        private var tvCategoryName: TextView? = view.findViewById(R.id.tvCategoryName)
        private var ivCategoryLogo: ImageView? = view.findViewById(R.id.lev)

        fun bind(category: Category, listener: Listener) {

            ivCategoryLogo?.context?.let {
                Glide.with(it).load(category.image).into(ivCategoryLogo!!)
            }
            tvCategoryName?.text = category.name
            itemView.setOnClickListener {
                listener.onClick(category)
            }
        }
    }

    interface Listener {
        fun onClick(category: Category)
    }
}