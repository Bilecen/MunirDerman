package com.munirderman.adapters

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.stephenvinouze.advancedrecyclerview.pagination.extensions.appendItems
import com.github.stephenvinouze.advancedrecyclerview.pagination.extensions.enablePagination
import com.munirderman.R
import kotlinx.android.synthetic.main.pagination_recycler_layout.*

/**
 * Created by Stephen Vinouze on 06/11/2015.
 */
class PaginationRecyclerFragment : Fragment() {

    private val handler = Handler()

    private val paginationAdapter: SamplePaginationAdapter by lazy {
        SamplePaginationAdapter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.pagination_recycler_layout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        paginationRecyclerView.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            itemAnimator = DefaultItemAnimator()
            adapter = paginationAdapter
            enablePagination(
                    isLoading = {
                        paginationAdapter.isLoading
                    },
                    hasAllItems = {
                        false
                    },
                    onLoad = {
                        populatePage(false, delayed = true)
                    })
        }

        refreshLayout.setOnRefreshListener { populatePage(true, delayed = true) }

        populatePage(true)
    }

    private fun populatePage(reload: Boolean, delayed: Boolean = false) {
        paginationAdapter.isLoading = true

        if (delayed) {
            handler.postDelayed({
                loadPage(reload)
            }, 2000)
        } else {
            loadPage(reload)
        }
    }

    private fun loadPage(reload: Boolean) {
//        val items = Sample.mockItems(itemsAssets)
//        if (reload) {
//            paginationAdapter.items = items.toMutableList()
//        } else {
//            paginationAdapter.appendItems(items)
//        }
//
//        paginationAdapter.isLoading = false
//        refreshLayout.isRefreshing = false
    }
}
