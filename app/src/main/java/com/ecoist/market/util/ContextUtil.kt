package com.ecoist.market.util

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.openLink(link: String = "https://t.me/ecoistukraine") {
    val uri = Uri.parse(
        link
    )
    startActivity(Intent(Intent.ACTION_VIEW, uri))
}