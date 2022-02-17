package com.ecoist.market.data.roomdb

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ecoist.market.R

/**
 *Created by Yehor Kudimov on 7/21/2021.
 */
var apiForTelegram="https://api.telegram.org/bot621215509:AAHSUiHESgLrqGh--MWOK251DprvM9caMLM/sendMessage?chat_id=541974507&text="

@BindingAdapter(value = ["setLike"])
fun ImageView.setLike(isLove: Boolean) {
    if (isLove) {
        Glide.with(context).load(R.drawable.favorlike).into(this)
    } else {
        Glide.with(context).load(R.drawable.favornewno).into(this)
    }
}

@BindingAdapter(value = ["setBucket"])
fun ImageView.setBucket(isLove: Boolean) {
    if (!isLove) {
        Glide.with(context).load(R.drawable.ic_baseline_add_shopping_cart_24).into(this)
    } else {
        Glide.with(context).load(R.drawable.ic_baseline_remove_shopping_cart_24).into(this)
    }
}
