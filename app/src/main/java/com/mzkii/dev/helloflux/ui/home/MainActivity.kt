package com.mzkii.dev.helloflux.ui.home

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mzkii.dev.helloflux.R
import com.mzkii.dev.helloflux.databinding.ActivityMainBinding
import com.mzkii.dev.helloflux.observe
import com.mzkii.dev.helloflux.ui.FetchMoreScrollListener
import com.mzkii.dev.helloflux.ui.notification.NotificationFragment
import com.mzkii.dev.helloflux.ui.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val store: HomeStore by viewModel()
    private val actionCreator: HomeActionCreator by inject()

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    private val repositoryAdapter: RepositoryAdapter by lazy {
        RepositoryAdapter(
            onCardClick = { repository ->
                repository.url?.let { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it))) }
            }
        )
    }

    private var isLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        observeState()
        isLoading = true
        actionCreator.getMyRepositoryList(1)
        bottom_navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun initView() {
        title = "My repositories"
        with(binding.recyclerView) {
            adapter = repositoryAdapter
            layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
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

    private fun observeState() {
        store.loadingState.observe(this) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            isLoading = it
        }
        store.loadedRepositoryListState.observe(this) {
            repositoryAdapter.submitList(it.toList())
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_home -> {
                val fragment = HomeFragment()
                print("mOnNavigationItemSelectedListener")
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                val fragment = SearchFragment()
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                val fragment = NotificationFragment()
                supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment, fragment.javaClass.getSimpleName())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
