package com.ecoist.market

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ecoist.market.presentation.base.BaseBottomTabFragment

class ContactsFramgent : BaseBottomTabFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts_framgent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.telegram).setOnClickListener {
            var uri = Uri.parse(
                "https://t.me/ecoistukraine"
            )
            startActivity(Intent(Intent.ACTION_VIEW,uri))
        }
    }

}