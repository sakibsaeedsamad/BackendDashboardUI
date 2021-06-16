package com.sssakib.backenddashboardui.view

import androidx.compose.Composable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.res.dimensionResource
import androidx.ui.unit.dp
import com.sssakib.backenddashboardui.R
import com.sssakib.backenddashboardui.components.*
import com.sssakib.backenddashboardui.model.Dashboard
import com.sssakib.backenddashboardui.model.ItemViewType
import com.sssakib.backenddashboardui.model.SubItemViewType

@Composable
fun showDashboard(data: List<Dashboard.Item>) {
    VerticalScroller {
        Column(
            modifier = LayoutPadding(
                top = dimensionResource(id = R.dimen.padding),
                bottom = dimensionResource(id = R.dimen.padding)
            )
        ) {
            data.forEachIndexed { index, item ->
                when (item.viewType) {
                    ItemViewType.HorizontalScroll -> showHorizontalElements(
                        item = item
                    )
                    ItemViewType.VerticalScroll -> showVerticalElements(
                        item = item
                    )
                }
                if (index != item.data.size) Spacer(modifier = LayoutHeight(10.dp))
            }
        }
    }
}

private fun showHorizontalElements(item: Dashboard.Item) {
    item.header?.let {
        showHeader(title = it.title, hasMore = it.hasMore)
    }
    horizontalScroll {
        item.data.forEachIndexed { index, data ->
            when (data.viewType) {
                SubItemViewType.Banner -> showBannerElement(
                    item = data
                )
                SubItemViewType.Category -> showCategoryElement(
                    item = data
                )
                else -> {
                    // do nothing
                }
            }
            if (index != item.data.size) Spacer(modifier = LayoutWidth(10.dp))
        }
    }
}

private fun showVerticalElements(item: Dashboard.Item) {
    item.header?.let {
        showHeader(title = it.title, hasMore = it.hasMore)
    }
    item.data.forEachIndexed { index, data ->
        when (data.viewType) {
            SubItemViewType.Restaurant -> showRestaurantElement(
                item = data
            )
            else -> {
                // do nothing
            }
        }
        if (index != item.data.size) showVerticalDivider()
    }
}
