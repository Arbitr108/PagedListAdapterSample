package com.danser.paged_list_sample.view

import com.danser.paged_delegate_adapter.IComparableItem
import com.danser.paged_delegate_adapter.KDelegateAdapter
import com.danser.paged_list_sample.R
import com.danser.paged_list_sample.domain.FeedItem
import kotlinx.android.synthetic.main.item_advert.*

class AdvertAdapter(
    private val onHideClick: (FeedItem.Advert) -> Unit
): KDelegateAdapter<AdvertViewModel>() {
    override val layoutId: Int = R.layout.item_advert

    override fun isForViewType(item: Any): Boolean = item is AdvertViewModel

    override fun onBind(item: AdvertViewModel, viewHolder: KViewHolder) = with(viewHolder) {
        bHide.setOnClickListener { onHideClick(item.payload) }
    }
}

data class AdvertViewModel(
    val payload: FeedItem.Advert
): IComparableItem {
    override fun id(): Any = this::class.java
    override fun content(): Any = this

}
