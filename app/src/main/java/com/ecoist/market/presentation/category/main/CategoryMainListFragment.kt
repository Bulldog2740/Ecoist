package com.ecoist.market.presentation.category.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ecoist.market.R
import com.ecoist.market.data.roomdb.CategoryModel
import com.ecoist.market.data.roomdb.RoomCatListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryMainListFragment : Fragment(), RoomCatListAdapter.Listener {
    private val viewModel: CategoryMainListViewModel by viewModel()
    private val categoryListObserver = Observer<List<CategoryModel>>(::handleCategoryListModel)
    private var recyclerView: RecyclerView? = null
    private val adapter = RoomCatListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_category_main_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager =
            LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        viewModel.resource.observe(viewLifecycleOwner) { result ->
            adapter.submitList(result.data)
        }
    }

    private fun handleCategoryListModel(categoryList: List<CategoryModel>?) {
        if (categoryList == null) return
        adapter.submitList(categoryList)
    }

    override fun onClick(category: CategoryModel) {
        val action =
            CategoryMainListFragmentDirections.actionCategoryListFragmentToCategoryCommonListFragment(
                category = category,
                categoryId = category.id
            )
        findNavController().navigate(action)
    }
}