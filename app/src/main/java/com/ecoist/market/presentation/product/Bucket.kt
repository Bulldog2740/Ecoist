package com.ecoist.market.presentation.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ecoist.market.R
import com.ecoist.market.data.model.ProductModel
import com.ecoist.market.databinding.FragmentBucketBinding
import com.ecoist.market.presentation.base.BaseBottomTabFragment
import com.ecoist.market.presentation.product.adapter.ProductListAdapter
import com.ecoist.market.presentation.product.list.ProductListViewModel
import com.ecoist.market.util.oneTimeCoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

/**
 *Created by Yehor Kudimov on 8/6/2021.
 */

class Bucket : BaseBottomTabFragment(), ProductListAdapter.Listener {

    private val viewModel: ProductListViewModel by inject()
    private val adapter =
        ProductListAdapter(this, viewModel)
    private lateinit var binding: FragmentBucketBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_bucket, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerBucket.adapter = adapter
        binding.recyclerBucket.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        lifecycleScope.launch(Dispatchers.Main) {
            viewModel.bucket().collect { adapter.submitList(it) }
        }
        binding.buttonBucketBuy.setOnClickListener{
            findNavController().navigate(BucketDirections.actionBucketFragmentToSayYourName())
        }
    }

    override fun onClick(product: ProductModel) {
        val action =
            BucketDirections.actionBucketFragmentToProductFragment(product)
        findNavController().navigate(action)
    }

    override fun onLike(product: ProductModel) {
        oneTimeCoroutineScope(Dispatchers.IO).launch {
            viewModel.checkFav(product)
        }
    }
    data class LilProduct(
        var alias:String?,
        var price:String?,
        var urlFoto:String?,
    )
}