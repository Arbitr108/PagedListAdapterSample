package com.danser.paged_list_sample.domain

import com.danser.paged_list_sample.repository.AdvertsRepository
import com.danser.paged_list_sample.repository.IAdvertsRepository
import com.danser.paged_list_sample.repository.IOffersRepository
import com.danser.paged_list_sample.repository.OffersRepository

class FeedInteractor(
    private val offersRepo: IOffersRepository = OffersRepository(),
    private val advertsRepo: IAdvertsRepository = AdvertsRepository()
) {
    fun getFeedItems(startPosition: Int, pageSize: Int): List<FeedItem> = offersRepo
        .getItems(startPosition, pageSize)
        .mapIndexed { idx, item ->
            when {
                idx % 4 == 3 -> listOf(item) + advertsRepo.getItems(startPosition + idx, 1)
                else -> listOf(item)
            }
        }
        .flatten()
        .apply { Thread.sleep(300) } //emulated delay

    fun removeAdvert(id: String) {
        advertsRepo.removeAdvert(id)
    }

    fun reset() {
        advertsRepo.reset()
    }
}
