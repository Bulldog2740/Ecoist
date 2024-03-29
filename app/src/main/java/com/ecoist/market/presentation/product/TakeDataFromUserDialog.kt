package com.ecoist.market.presentation.product

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
import androidx.lifecycle.lifecycleScope
import com.ecoist.market.R
import com.ecoist.market.databinding.BucketQusetionBinding
import com.ecoist.market.presentation.product.list.ProductListViewModel
import com.ecoist.market.presentation.product.model.ProductBaseModel
import kotlinx.coroutines.flow.collect
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
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.bucket_qusetion, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var mtbList = mutableListOf<ProductBaseModel>()
        lifecycleScope.launch {
            viewModel.bucket().collect { listProduct ->
                listProduct.forEach { product ->
                    mtbList.add(
                        ProductBaseModel(
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
                "Ім'я та номер",
                Toast.LENGTH_SHORT
            )
                .show()
        }
        binding.name.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrBlank()) {
                    Toast.makeText(
                        requireContext(),
                        "Ім'я та номер",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                } else {
                    if (s.length > 3) {
                        nameOfClient = binding.name.text.toString()
                        binding.etNumber.addTextChangedListener {
                            telephon = binding.etNumber.text.toString()
                            if (!telephon.isNullOrBlank() && telephon.length > 5) {
                                binding.buttonNext.setOnClickListener {
                                    lifecycleScope.launch {
                                        viewModel.sendMessageTelega("MODEL PHONE- " + getPhoneModel() + "-Ім'я-" + nameOfClient + "-Номер- " + telephon + "-ТОВАР-" + mtbList.toString())
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
        return "Device-" + Build.BRAND + "-" + Build.MODEL
    }
}
