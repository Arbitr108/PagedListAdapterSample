package com.danser.paged_list_sample.domain

sealed class FeedItem(open val id: String) {

    data class Offer(
        override val id: String,
        val price: Int,
        val title: String,
        val text: String
    ): FeedItem(id)

    data class Advert(
        override val id: String
    ): FeedItem(id)
}
