package com.example.atonce_admin.presentation.home.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce_admin.R
import com.example.atonce_admin.core.enums.OrderStatesEnum
import com.example.atonce_admin.domain.entity.OrderEntity
import com.example.atonce_admin.domain.entity.WarehouseEntity
import com.example.atonce_admin.presentation.common.component.EmptySearchResultView
import com.example.atonce_admin.presentation.common.component.WarehouseRowItem
import com.example.atonce_admin.presentation.common.component.WarehouseRowShimmerItem
import com.example.atonce_admin.presentation.common.theme.MediumFont
import com.example.atonce_admin.presentation.common.theme.RegularFont
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material3.placeholder
import com.google.accompanist.placeholder.material3.shimmer

@Composable
fun WareHousesSection(
    warehouses : List<WarehouseEntity>,
    onItemClick: (List<OrderEntity> , String) -> Unit,
    onSeeMoreClick: () -> Unit
) {
    val containerColor = MaterialTheme.colorScheme.surface
    val borderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
    val dividerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f)
    val seeMoreColor = MaterialTheme.colorScheme.primary

    Column(modifier = Modifier.padding(16.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = OrderStatesEnum.ORDERED.getLocalizedTitle(),
                fontSize = 18.sp,
                fontFamily = MediumFont
            )
            if (!warehouses.isEmpty()){
                Text(
                    text = stringResource(R.string.see_more),
                    color = seeMoreColor,
                    modifier = Modifier.clickable { onSeeMoreClick() },
                    fontSize = 14.sp,
                    fontFamily = RegularFont
                )
            }
        }

        Column(
            modifier = Modifier
                .padding(top = 8.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(containerColor)
                .border(1.dp, borderColor, RoundedCornerShape(12.dp))
        ) {

            if (warehouses.isEmpty()){
                EmptySearchResultView(
                    text = stringResource(R.string.currently_there_are_no_orders),
                    isLottie = false
                )
            }
            else{
                warehouses.forEachIndexed { index, order ->
                    if (index > 0) {
                        Divider(color = dividerColor)
                    }
                    WarehouseRowItem(
                        warehouse = order,
                        onItemClick = {
                                orders , warehouseName ->
                            onItemClick(orders , warehouseName)
                        }
                    )
                }
            }
        }
    }
}



@Composable
fun WareHousesSectionShimmer() {
    val containerColor = MaterialTheme.colorScheme.surface
    val borderColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
    val dividerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.05f)

    val isDark = isSystemInDarkTheme()
    val shimmerColor = if (isDark) Color(0xFF444444) else Color(0xFFC0C0C0)

    Column(modifier = Modifier.padding(16.dp)) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(
                modifier = Modifier
                    .width(120.dp)
                    .height(20.dp)
                    .placeholder(
                        visible = true,
                        color = shimmerColor,
                        highlight = PlaceholderHighlight.shimmer()
                    )
            )


            Spacer(
                modifier = Modifier
                    .width(60.dp)
                    .height(14.dp)
                    .placeholder(
                        visible = true,
                        color = shimmerColor,
                        highlight = PlaceholderHighlight.shimmer()
                    )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .background(containerColor)
                .border(1.dp, borderColor, RoundedCornerShape(12.dp))
        ) {
            repeat(3) { index ->
                if (index > 0) {
                    Divider(color = dividerColor)
                }
                WarehouseRowShimmerItem()
            }
        }
    }
}

