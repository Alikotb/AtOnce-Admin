package com.example.atonce_admin.presentation.profile.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.foundation.layout.size
import io.github.g0dkar.qrcode.QRCode

@Composable
fun QRCodeView(data: String, modifier: Modifier = Modifier) {
    val bitmap = remember(data) {
        QRCode(data).render().nativeImage() as Bitmap
    }

    Row(modifier = modifier.fillMaxWidth() , horizontalArrangement = Arrangement.Center){
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = "QR Code",
            contentScale = ContentScale.Fit,
            modifier = modifier.size(128.dp)
        )
    }
}
