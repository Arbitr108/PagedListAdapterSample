package com.danser.paged_list_sample.util

import androidx.paging.DataSource
import androidx.paging.PositionalDataSource

class ItemsDataSource<T>(
    private val itemsProvider: (Int, Int) -> List<T>
) : PositionalDataSource<T>() {

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<T>) {
        val result: List<T> = itemsProvider(params.requestedStartPosition, params.requestedLoadSize)
        callback.onResult(result, params.requestedStartPosition)
    }

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<T>) {
        val result: List<T> = itemsProvider(params.startPosition, params.loadSize)
        callback.onResult(result)
    }
}

class ItemsDataSourceFactory<T>(
    private val itemsProvider: (Int, Int) -> List<T>
) : DataSource.Factory<Int, T>() {
    override fun create(): DataSource<Int, T> = ItemsDataSource(itemsProvider)
}
