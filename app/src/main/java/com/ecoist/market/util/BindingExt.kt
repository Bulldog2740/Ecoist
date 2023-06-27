package com.ecoist.market.util

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ecoist.market.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

@BindingAdapter(value = ["setLikeFLOAT"])
fun FloatingActionButton.setLikeFLOAT(isLove: Boolean) {
    if (isLove) {

        Glide.with(context).load(R.drawable.favorlike).into(this)
    } else {
        Glide.with(context).load(R.drawable.favornewno).into(this)
    }
}