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
import com.ecoist.market.data.roomdb.PhotoModel
import com.ecoist.market.data.roomdb.ProductModel
import com.ecoist.market.data.roomdb.Resource
import com.ecoist.market.databinding.FragmentProductBinding
import com.ecoist.market.presentation.product.adapter.PhotoListAdapter
import com.ecoist.market.presentation.product.list.ProductListViewModel
import org.koin.android.ext.android.inject

class ProductFragment : Fragment() {
    private lateinit var bind: FragmentProductBinding
    private val args: ProductFragmentArgs by navArgs()
    private val viewModel: ProductViewModel by inject()
    private val productObserver = Observer(::handleProduct)
    private val photoObserver = Observer<Resource<List<PhotoModel>>>(::handlePhoto)
    private var recyclerView: RecyclerView? = null
    private var tvProductName: TextView? = null
    private var tvProductDescription: TextView? = null
    private var tvProductDescriptionFull: TextView? = null
    private var tvProductPrice: TextView? = null
    private var tvProductPriceCena: TextView? = null
    private var tvComment: TextView? = null
    private var tvTextX: TextView? = null
    private var tvComentX: TextView? = null
    private var tvProductTextFullX: TextView? = null
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
            viewModel.buyEcoTovar(args.product)
        }
        bind.nablike.setOnClickListener {
            viewModel.checkFav(args.product)
        }
        tvProductName = view.findViewById(R.id.tvProductName)
        tvProductDescriptionFull = view.findViewById(R.id.tvProductTextFull)
        tvProductDescription = view.findViewById(R.id.tvText)
        tvProductPrice = view.findViewById(R.id.price)
        tvProductPriceCena = view.findViewById(R.id.priceName)
        tvComment = view.findViewById(R.id.tvComment)
        tvProductTextFullX = view.findViewById(R.id.tvProductTextFullX)
        tvTextX = view.findViewById(R.id.tvTextX)
        tvComentX = view.findViewById(R.id.tvComentX)
        recyclerView = view.findViewById(R.id.photo_recycler)
        recyclerView?.adapter = listAdapter
        recyclerView?.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.HORIZONTAL, false)
        viewModel.product(str).observe(viewLifecycleOwner, productObserver)
        viewModel.photo(args.product.galleryName).observe(viewLifecycleOwner) {
            listAdapter.submitList(it.data)

        }
    }

    private fun handleProduct(product: Resource<ProductModel>?) {
        if (product == null) return
        tvProductName?.text = product.data?.name
        tvProductPrice?.text = product.data?.price
        tvProductDescriptionFull?.text = product.data?.descriptionFull?.fromHtml()?.trim()
        tvProductDescription?.text = product.data?.description?.fromHtml()?.trim()
        tvComment?.text = product.data?.coment?.fromHtml()?.trim()
    }

    private fun handlePhoto(ph: Resource<List<PhotoModel>>?) {
        if (ph == null) return
        listAdapter.submitList(ph.data)
    }

    private fun String.fromHtml(): Spanned {
        return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

}
