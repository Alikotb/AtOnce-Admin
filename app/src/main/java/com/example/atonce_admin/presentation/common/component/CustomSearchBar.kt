package com.example.atonce_admin.presentation.common.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce_admin.R
import com.example.atonce_admin.presentation.common.theme.RegularFont

@Composable
fun CustomSearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    placeholder: String = stringResource(R.string.search),
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp)
        .height(40.dp)
        .clip(RoundedCornerShape(8.dp))
        .background(Color(0xFFF0F0F0))
) {
    BasicTextField(
        value = query,
        onValueChange = onQueryChange,
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(
            fontSize = 14.sp,
            color = Color.Black,
            fontFamily = RegularFont
        ),
        modifier = modifier.padding(horizontal = 8.dp),
        decorationBox = { innerTextField ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Gray,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (query.isEmpty()) {
                        Text(
                            text = placeholder,
                            fontSize = 14.sp,
                            color = Color.Gray,
                            fontFamily = RegularFont
                        )
                    }
                    innerTextField()
                }
            }
        }
    )
}



