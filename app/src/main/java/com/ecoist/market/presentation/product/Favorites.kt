package com.ecoist.market.presentation.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.ecoist.market.R
import com.ecoist.market.data.roomdb.ProductModel
import com.ecoist.market.data.roomdb.Resource
import com.ecoist.market.databinding.FavoritesFragmentBinding
import com.ecoist.market.presentation.product.adapter.ProductListAdapter
import com.ecoist.market.presentation.product.list.ProductListFragmentArgs
import com.ecoist.market.presentation.product.list.ProductListViewModel
import org.koin.android.ext.android.inject
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecoist.market.presentation.base.BaseBottomTabFragment
import com.ecoist.market.presentation.product.list.ProductListFragmentDirections
import com.ecoist.market.util.oneTimeCoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 *Created by Yehor Kudimov on 7/26/2021.
 */

class Favorites : BaseBottomTabFragment(), ProductListAdapter.Listener {
    private val viewModel: ProductListViewModel by inject()
    private val adapter =
        ProductListAdapter(this, viewModel)

    private lateinit var binding: FavoritesFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.favorites_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewFavorites?.adapter = adapter
        binding.recyclerViewFavorites?.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        lifecycleScope.launch (Dispatchers.Main){
            viewModel.favoriteLatestNews().collect { adapter.submitList(it) }
        }
    }
    override fun onClick(product: ProductModel) {
        val action =
            FavoritesDirections.actionFavorites2ToProductFragment(product)
        findNavController().navigate(action)
    }

    override fun onLike(product: ProductModel) {
        oneTimeCoroutineScope(Dispatchers.IO).launch {
            viewModel.checkFav(product)
        }
    }

}