package com.danser.paged_list_sample.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.danser.paged_delegate_adapter.IComparableItem
import com.danser.paged_list_sample.domain.FeedInteractor
import com.danser.paged_list_sample.domain.FeedItem
import com.danser.paged_list_sample.util.ItemsDataSourceFactory
import java.util.concurrent.Executors

class FeedPresentationModel(
    private val interactor: FeedInteractor = FeedInteractor(),
    private val feedVMFactory: FeedVMFactory = FeedVMFactory()
): ViewModel() {

    val pagedListLiveData: LiveData<PagedList<IComparableItem>> = getPagedList(
        ItemsDataSourceFactory(itemsProvider = ::getItems)
    )

    fun onHideAdvertClicked(advert: FeedItem.Advert) {
        //remove advert from the list
        interactor.removeAdvert(advert.id)
        update()
    }

    fun onRefresh() {
        //reload all list
        interactor.reset()
        update()
    }

    private fun getItems(startPosition: Int, pageSize: Int): List<IComparableItem> {
        val items = interactor.getFeedItems(startPosition, pageSize)
        return feedVMFactory.toViewModel(items)
    }

    private fun update() {
        pagedListLiveData.value?.dataSource?.invalidate()
    }

    private fun getPagedList(
        dataSourceFactory: DataSource.Factory<Int, IComparableItem>
    ): LiveData<PagedList<IComparableItem>> {

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        return LivePagedListBuilder(dataSourceFactory, config)
            .setFetchExecutor(Executors.newSingleThreadExecutor())
            .build()
    }

}
