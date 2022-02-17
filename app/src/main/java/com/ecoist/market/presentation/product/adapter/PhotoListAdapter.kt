package com.ecoist.market.presentation.product.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ecoist.market.R
import com.ecoist.market.data.roomdb.PhotoModel

/**
 *Created by Yehor Kudimov on 11.05.2021.
 */
class PhotoListAdapter(

) : ListAdapter<PhotoModel, PhotoListAdapter.PhotoVH>(diff) {

    companion object {
        val diff = object : DiffUtil.ItemCallback<PhotoModel>() {
            override fun areItemsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean {
                return oldItem.id == newItem.id && oldItem.name == newItem.name
            }

            override fun areContentsTheSame(oldItem: PhotoModel, newItem: PhotoModel): Boolean {
                return oldItem.imageUrl == newItem.imageUrl
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoVH {
        val photoView = LayoutInflater.from(parent.context)
            .inflate(R.layout.photo_item, parent, false)
        return PhotoVH(photoView)
    }

    override fun onBindViewHolder(holder: PhotoVH, position: Int) {
        val item = getItem(position)
        if (item != null) holder.bind(item)
    }

    class PhotoVH(view: View) : RecyclerView.ViewHolder(view) {
        private val photoProduct: ImageView? = itemView.findViewById(R.id.tvPhotoItem)
        fun bind(photo: PhotoModel) {
            photoProduct?.context?.let {
                Glide.with(it).load(photo.imageUrl).into(photoProduct)
            }
        }
    }


}