package com.ecoist.market.presentation.product.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.ecoist.market.R
import com.ecoist.market.data.model.ProductModel
import com.ecoist.market.util.Resource
import com.ecoist.market.presentation.product.adapter.ProductListAdapter
import com.ecoist.market.util.oneTimeCoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class ProductListFragment : Fragment(), ProductListAdapter.Listener {

    private val args: ProductListFragmentArgs by navArgs()
    private val viewModel: ProductListViewModel by inject()
    private val productListObserver = Observer<Resource<List<ProductModel>>>(::handleProductList)
    private var recyclerView: RecyclerView? = null
    private val adapter =
        ProductListAdapter(this,viewModel)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        viewModel.listProducts(args.category!!.id).observe(viewLifecycleOwner, productListObserver)
    }

    override fun onClick(product: ProductModel) {
        val action =
            ProductListFragmentDirections.actionProductListFragmentToProductFragment(product)
        findNavController().navigate(action)
    }

    override fun onLike(product: ProductModel) {
        oneTimeCoroutineScope(Dispatchers.IO).launch {
            viewModel.checkFav(product)
        }
    }


    private fun handleProductList(productList: Resource<List<ProductModel>>?) {
        if (productList == null) return
        adapter.submitList(productList.data)
    }
}
