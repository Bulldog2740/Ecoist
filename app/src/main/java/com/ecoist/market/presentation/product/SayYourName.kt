package com.ecoist.market.presentation.product

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.asFlow
import androidx.lifecycle.lifecycleScope
import com.ecoist.market.R
import com.ecoist.market.data.roomdb.apiForTelegram
import com.ecoist.market.databinding.BucketQusetionBinding
import com.ecoist.market.databinding.FragmentBucketBinding
import com.ecoist.market.domain.repository.TelegramRepository
import com.ecoist.market.presentation.product.list.ProductListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

var nameOfClient = ""
var telephon = ""

class SayYourName : DialogFragment() {
    private lateinit var binding: BucketQusetionBinding
    private val viewModel: ProductListViewModel by inject()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.bucket_qusetion, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var mtbList = mutableListOf<Bucket.LilProduct>()
        lifecycleScope.launch {
            viewModel.bucket().collect { listProduct ->
                listProduct.forEach { product ->
                    mtbList.add(
                        Bucket.LilProduct(
                            product.alias,
                            product.price,
                            product.imageUrl

                        )
                    )
                }
            }
        }
        if (binding.name.text.isNullOrBlank()) {
            Toast.makeText(
                requireContext(),
                "Укажите имя и номер телефона",
                Toast.LENGTH_SHORT
            )
                .show()
        }
        binding.name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrBlank()) {
                    Toast.makeText(
                        requireContext(),
                        "Укажите имя и номер телефона",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    if (s.length > 3) {
                        nameOfClient = binding.name.text.toString()
                        binding.phoneNum.addTextChangedListener {
                            telephon = binding.phoneNum.text.toString()
                            if (!telephon.isNullOrBlank()&&telephon.length>5){
                                binding.ok.setOnClickListener {
                                    lifecycleScope.launch {
                                        viewModel.sendMessageTelega("MODEL PHONE- "+getPhoneModel() +"-Имя Клиент-"+ nameOfClient + "-Номер- " + telephon + "-ТОВАРЫ-" + mtbList.toString())
                                        dismiss()
                                    }
                                }
                            }
                        }

                    }
                }
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

    }

    private fun getPhoneModel(): String? {
        return "Device-"+Build.BRAND +"-" + Build.MODEL
    }
}
