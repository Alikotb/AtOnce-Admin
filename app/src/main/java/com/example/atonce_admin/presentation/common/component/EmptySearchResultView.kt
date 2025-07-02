package com.example.atonce_admin.presentation.common.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.atonce_admin.R
import com.example.atonce_admin.presentation.common.theme.MediumFont
import com.example.atonce_admin.presentation.login.components.PrimaryButton

@Composable
fun EmptySearchResultView(
    text: String = stringResource(R.string.no_results_found),
    isLottie: Boolean = true
) {

    val screenWidth = LocalConfiguration.current.screenWidthDp
    val screenHeight = LocalConfiguration.current.screenHeightDp

    val lottieSize = Pair(screenWidth * 0.8, screenHeight * 0.3)


    val composition = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.fly))
    val progress = animateLottieCompositionAsState(
        composition = composition.value,
        iterations = DEFAULT_BUFFER_SIZE,
        speed = 1.0f,
        isPlaying = true
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            if (isLottie){
                LottieAnimation(
                    composition = composition.value,
                    progress = { progress.value },
                    modifier = Modifier
                        .width(lottieSize.first.dp)
                        .height(lottieSize.second.dp)
                )
            }
            Text(
                text = text,
                fontFamily = MediumFont,
                fontSize = 16.sp
            )
        }


    }
}
