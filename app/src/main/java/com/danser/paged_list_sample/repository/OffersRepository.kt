package com.danser.paged_list_sample.repository

import com.danser.paged_list_sample.domain.FeedItem

interface IOffersRepository {
    fun getItems(startPosition: Int, count: Int): List<FeedItem.Offer>
}

class OffersRepository : IOffersRepository {

    override fun getItems(startPosition: Int, count: Int): List<FeedItem.Offer> =
        (0 until count).map { id ->
            val id = startPosition + id
            val item = when (id % 3) {
                0 -> AUDI
                1 -> BMW
                else -> LADA
            }
            item.copy(
                id = "$id",
                title = "$id) ${item.title}"
            )
        }


    companion object {
        private val AUDI = FeedItem.Offer(
            id = "0",
            title = "Audi A5б 2017",
            text = "Машина в идеальном состоянии",
            price = 30000000
        )
        private val BMW = FeedItem.Offer(
            id = "0",
            title = "BMW X6, 2019",
            text = "Машина только что из салона",
            price = 400000
        )
        private val LADA = FeedItem.Offer(
            id = "0",
            title = "Лада ВАЗ, 1999",
            text = "Гроб на колесах",
            price = 20000
        )
    }
}
