package com.danser.paged_list_sample.view

import com.danser.paged_delegate_adapter.IComparableItem
import com.danser.paged_delegate_adapter.KDelegateAdapter
import com.danser.paged_list_sample.R
import com.danser.paged_list_sample.domain.FeedItem
import kotlinx.android.synthetic.main.item_offer.*


class OfferAdapter : KDelegateAdapter<OfferViewModel>() {
    override val layoutId: Int = R.layout.item_offer

    override fun isForViewType(item: Any): Boolean = item is OfferViewModel

    override fun onBind(item: OfferViewModel, viewHolder: KViewHolder) = with(viewHolder) {
        tvTitle.text = item.title
        tvText.text = item.text
    }
}


data class OfferViewModel(
    val title: String,
    val price: String,
    val text: String,
    val payolad: FeedItem.Offer
): IComparableItem {

    override fun id(): Any = this::class.java.simpleName
    override fun content(): Any = this
}
