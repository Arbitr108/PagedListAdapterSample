package com.danser.paged_list_sample.presentation

import com.danser.paged_delegate_adapter.IComparableItem
import com.danser.paged_list_sample.domain.FeedItem
import com.danser.paged_list_sample.view.AdvertViewModel
import com.danser.paged_list_sample.view.DividerItem
import com.danser.paged_list_sample.view.OfferViewModel
import java.text.DecimalFormat

class FeedVMFactory {

    private val decimalFormat: DecimalFormat = DecimalFormat("###,###.#")

    fun toViewModel(items: List<FeedItem>): List<IComparableItem> =
        items.map { item ->
            when (item) {
                is FeedItem.Offer -> item.toViewModel()
                is FeedItem.Advert -> item.toViewModel()
            }
        }.map { listOf(it, DividerItem) }
            .flatten()

    private fun FeedItem.Offer.toViewModel(): OfferViewModel = OfferViewModel(
        title = title,
        price = decimalFormat.format(price) + " Р",
        text = text,
        payolad = this
    )

    private fun FeedItem.Advert.toViewModel(): AdvertViewModel = AdvertViewModel(
        payload = this
    )
}
