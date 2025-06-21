package com.example.atonce_admin.presentation.common.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce_admin.R
import com.example.atonce_admin.presentation.common.theme.MediumFont

@Composable
fun EmptySearchResultView(
    text: String = stringResource(R.string.no_results_found)
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontFamily = MediumFont,
            fontSize = 16.sp
        )
    }
}
