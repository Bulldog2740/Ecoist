package com.ecoist.market.presentation.product.single

import android.os.Bundle
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ecoist.market.R
import com.ecoist.market.data.model.PhotoModel
import com.ecoist.market.data.model.ProductModel
import com.ecoist.market.util.Resource
import com.ecoist.market.databinding.FragmentProductBinding
import com.ecoist.market.presentation.product.adapter.PhotoListAdapter
import com.ecoist.market.util.fromHtml
import org.koin.android.ext.android.inject

class ProductFragment : Fragment() {
    private lateinit var bind: FragmentProductBinding
    private val args: ProductFragmentArgs by navArgs()
    private val viewModel: ProductViewModel by inject()
    private val productObserver = Observer(::handleProduct)
    private val photoObserver = Observer<Resource<List<PhotoModel>>>(::handlePhoto)
    private val listAdapter = PhotoListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater, R.layout.fragment_product, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var str = args.product.id
        bind.model = args.product
        bind.executePendingBindings()
        bind.nabBuy.setOnClickListener {
            viewModel.buyProduct(args.product)
        }
        bind.nablike.setOnClickListener {
            viewModel.checkFav(args.product)
        }
        bind.photoRecycler.adapter = listAdapter
        bind.photoRecycler.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        viewModel.product(str).observe(viewLifecycleOwner, productObserver)
        viewModel.photo(args.product.galleryName).observe(viewLifecycleOwner) {
            listAdapter.submitList(it.data)

        }
    }

    private fun handleProduct(product: Resource<ProductModel>?) {
        if (product == null) return
        bind.tvProductName.text = product.data?.name
        bind.price.text = product.data?.price
        bind.tvProductTextFull.text = product.data?.descriptionFull?.fromHtml()?.trim()
        bind.tvText.text = product.data?.description?.fromHtml()?.trim()
        bind.tvComment.text = product.data?.coment?.fromHtml()?.trim()
    }

    private fun handlePhoto(ph: Resource<List<PhotoModel>>?) {
        if (ph == null) return
        listAdapter.submitList(ph.data)
    }
}
