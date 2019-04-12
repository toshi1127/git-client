package com.mzkii.dev.helloflux.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mzkii.dev.helloflux.R
import com.mzkii.dev.helloflux.ui.FetchMoreScrollListener
import kotlinx.android.synthetic.main.home_activity.view.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val store: HomeStore by viewModel()
    private val actionCreator: HomeActionCreator by inject()
    private var isLoading = false
    private val repositoryAdapter: RepositoryAdapter by lazy {
        RepositoryAdapter(
            onCardClick = { repository ->
                repository.url?.let { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it))) }
            }
        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.home_activity, container, false)
        initView(view.recycler_view)
        return view
    }

    private fun initView(recycler_view: RecyclerView) {
        val recyclerView = recycler_view
        with(recyclerView) {
            recyclerView.adapter = repositoryAdapter
            recyclerView.layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(object : FetchMoreScrollListener() {
                override fun onLoadMore() {
                    if (!isLoading && store.canFetchMore) {
                        isLoading = true
                        actionCreator.getMyRepositoryList(store.pageNum)
                    }
                }
            })
        }
    }
}