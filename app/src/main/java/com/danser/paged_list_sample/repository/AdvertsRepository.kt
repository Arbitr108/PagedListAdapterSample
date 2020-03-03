package com.danser.paged_list_sample.repository

import com.danser.paged_list_sample.domain.FeedItem

interface IAdvertsRepository {
    fun getItems(startPosition: Int, pageSize: Int): List<FeedItem.Advert>
    fun removeAdvert(id: String)
    fun reset()
}

class AdvertsRepository : IAdvertsRepository {

    private val removedIds = mutableListOf<String>()

    override fun getItems(startPosition: Int, pageSize: Int): List<FeedItem.Advert> =
        (startPosition until startPosition + pageSize).map { idx ->
            FeedItem.Advert(
                id = "$idx"
            )
        }.filter { !removedIds.contains(it.id) }

    override fun removeAdvert(id: String) {
        removedIds.add(id)
    }

    override fun reset() {
        removedIds.clear()
    }
}
