package com.ecoist.market.presentation.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ecoist.market.R
import com.ecoist.market.presentation.base.BaseBottomTabFragment
import com.ecoist.market.util.openLink

class ContactsFragment : BaseBottomTabFragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contacts_framgent, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.telegram).setOnClickListener {
            requireActivity().openLink()
        }
    }
}