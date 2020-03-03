package com.danser.paged_list_sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.danser.paged_delegate_adapter.IComparableItem
import com.danser.paged_delegate_adapter.PagedDiffAdapter
import com.danser.paged_list_sample.presentation.FeedPresentationModel
import com.danser.paged_list_sample.view.AdvertAdapter
import com.danser.paged_list_sample.view.DividerAdapter
import com.danser.paged_list_sample.view.OfferAdapter
import kotlinx.android.synthetic.main.activity_feed.*

class FeedActivity : AppCompatActivity() {

    private lateinit var presentation: FeedPresentationModel

    private val adapter: PagedDiffAdapter by lazy { getDiffAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        initPresentationModel()
        bindRecycler()
        bindSwipeRefresh()
    }

    private fun initPresentationModel() {
        presentation = ViewModelProviders.of(this)[FeedPresentationModel::class.java]

        presentation.pagedListLiveData.observe(this, Observer { pagedList ->
            update(pagedList)
        })
    }

    private fun bindRecycler() {
        rvList.adapter = adapter
        rvList.layoutManager = LinearLayoutManager(this)
    }

    private fun bindSwipeRefresh() {
        vSwipeRefresh.setOnRefreshListener { presentation.onRefresh() }
    }

    private fun update(pagedList: PagedList<IComparableItem>) {
        vSwipeRefresh.isRefreshing = false
        adapter.submitList(pagedList)
    }


    private fun getDiffAdapter(): PagedDiffAdapter = PagedDiffAdapter(
        delegateAdapters = listOf(
            OfferAdapter(),
            AdvertAdapter(presentation::onHideAdvertClicked),
            DividerAdapter
        )
    )
}
