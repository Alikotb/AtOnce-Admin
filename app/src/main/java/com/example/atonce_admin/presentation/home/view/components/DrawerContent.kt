package com.example.atonce_admin.presentation.home.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AssignmentReturned
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.NewReleases
import androidx.compose.material.icons.outlined.People
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.atonce_admin.R
import com.example.atonce_admin.core.enums.OrderStatesEnum
import com.example.atonce_admin.core.extensions.convertNumbersToArabic
import com.example.atonce_admin.domain.entity.UserEntity
import com.example.atonce_admin.presentation.common.theme.MediumFont
import com.example.atonce_admin.presentation.common.theme.RegularFont

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun DrawerContent(
    userData : UserEntity,
    onProfileClicked: () -> Unit,
    onCustomerClicked: () -> Unit,
    onItemClick: (OrderStatesEnum) -> Unit,
    onLogout: () -> Unit
) {
    val items = listOf(
        Pair(OrderStatesEnum.ORDERED, Icons.Outlined.NewReleases),
        Pair(OrderStatesEnum.DELIVERED, Icons.Outlined.LocalShipping),
        Pair(OrderStatesEnum.CANCELED, Icons.Outlined.Cancel),
        Pair(OrderStatesEnum.RETURNED, Icons.Outlined.AssignmentReturned)
    )

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight * 0.25f)
                .background(Color(0xFF009688))
                .padding(16.dp),
        ) {
            Column(modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.Start ,
                verticalArrangement = Arrangement.Center
            ) {
                GlideImage(
                    model = "https://randomuser.me/api/portraits/men/44.jpg",
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(userData.name, color = Color.White , fontSize = 16.sp , fontFamily = MediumFont)
                Text(userData.phone.convertNumbersToArabic(), color = Color.White, fontSize = 14.sp , fontFamily = RegularFont)
            }
        }

        DrawerItem(
            icon = Icons.Outlined.AccountCircle,
            title = stringResource(R.string.profile),
            onClick = onProfileClicked
        )

        Divider(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        DrawerItem(
            icon = Icons.Outlined.People,
            title = stringResource(R.string.customers),
            onClick = onCustomerClicked
        )

        items.forEach { (type, icon) ->
            DrawerItem(
                icon = icon,
                title = type.getLocalizedTitle(),
                onClick = { onItemClick(type) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Divider(
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
            thickness = 1.dp,
            modifier = Modifier.padding(vertical = 8.dp)
        )


        DrawerItem(
            icon = Icons.AutoMirrored.Default.Logout,
            title = stringResource(R.string.logout),
            iconColor = Color.Red,
            onClick = onLogout
        )
    }
}
