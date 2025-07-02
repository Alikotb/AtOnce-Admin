
package com.example.atonce_admin.presentation.common.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.atonce_admin.R
import com.example.atonce_admin.presentation.common.theme.PrimaryColor

@Composable
fun LogoIcon(
    modifier: Modifier = Modifier,
    iconColor: Color = Color.White,
    backgroundColor: Color = PrimaryColor
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.temp_logo),
            contentDescription = "Logo Icon",
            modifier = Modifier.size(128.dp)
        )
    }
}

@Preview
@Composable
fun LogoIconPreview() {
    LogoIcon(
        modifier = Modifier.background(Color.White)
    )
}
