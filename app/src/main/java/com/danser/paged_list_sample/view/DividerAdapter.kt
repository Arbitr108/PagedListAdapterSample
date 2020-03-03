package com.danser.paged_list_sample.view

import com.danser.paged_delegate_adapter.IComparableItem
import com.danser.paged_delegate_adapter.KDelegateAdapter
import com.danser.paged_list_sample.R

class DividerAdapter: KDelegateAdapter<DividerItem>() {
    override val layoutId: Int = R.layout.item_divider

    override fun isForViewType(item: Any): Boolean = item == DividerItem

    override fun onBind(item: DividerItem, viewHolder: KViewHolder) {
        //do nothing
    }

}

object DividerItem: IComparableItem {
    override fun id(): Any = this
    override fun content(): Any = this
}
