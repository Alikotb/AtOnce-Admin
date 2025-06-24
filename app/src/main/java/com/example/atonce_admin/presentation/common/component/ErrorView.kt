package com.example.atonce_admin.presentation.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.atonce_admin.R
import com.example.atonce_admin.presentation.common.theme.BoldFont
import com.example.atonce_admin.presentation.common.theme.MediumFont
import com.example.atonce_admin.presentation.common.theme.PrimaryColor
import com.example.atonce_admin.presentation.login.components.PrimaryButton

@Composable
fun ErrorView(message: String, onRetry: () -> Unit) {

    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp

    val buttonSize = Pair(screenWidth * 0.4, screenHeight * 0.05)

    val lottieSize = Pair(screenWidth * 0.8, screenHeight * 0.4)


    val composition = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.no_internet))
    val progress = animateLottieCompositionAsState(
        composition = composition.value,
        iterations = DEFAULT_BUFFER_SIZE,
        speed = 1.0f,
        isPlaying = true
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            LottieAnimation(
                composition = composition.value,
                progress = { progress.value },
                modifier = Modifier
                    .width(lottieSize.first.dp)
                    .height(lottieSize.second.dp)
            )
            Text(text = message , fontSize = 16.sp , fontFamily = MediumFont)
            Spacer(modifier = Modifier.height(8.dp))
            PrimaryButton(text = stringResource(R.string.retry),
                loading = false ,
                onClick = onRetry,
                modifier = Modifier
                    .width(buttonSize.first.dp)
                    .height(buttonSize.second.dp)
                , radius = 50
            )
        }
    }
}