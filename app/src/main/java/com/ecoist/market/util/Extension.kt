package com.ecoist.market.util

import android.text.Spanned
import android.widget.ImageView
import androidx.core.text.HtmlCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ecoist.market.R


@BindingAdapter(value = ["setLike"])
fun ImageView.setLike(isLove: Boolean) {
    if (isLove) {
        Glide.with(context).load(R.drawable.favorlike).into(this)
    } else {
        Glide.with(context).load(R.drawable.favornewno).into(this)
    }
}

fun String.fromHtml(): Spanned {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
}