package com.ecoist.market.util

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.view.View
import android.view.WindowInsets
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.ecoist.market.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

fun NavController.popBackStackAllInstances(destination: Int, inclusive: Boolean): Boolean {
    var popped: Boolean
    while (true) {
        popped = popBackStack(destination, inclusive)
        if (!popped) {
            break
        }
    }
    return popped
}

@BindingAdapter(value = ["setLikeFLOAT"])
fun FloatingActionButton.setLikeFLOAT(isLove: Boolean) {
    if (isLove) {

        Glide.with(context).load(R.drawable.favorlike).into(this)
    } else {
        Glide.with(context).load(R.drawable.favornewno).into(this)
    }
}