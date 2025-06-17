package com.example.atonce_admin.presentationLayer.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.atonce_admin.presentationLayer.theme.BoldFont
import com.example.atonce_admin.presentationLayer.theme.MediumFont
import com.example.atonce_admin.presentationLayer.theme.PrimaryColor
import com.example.atonce_admin.presentationLayer.theme.backgroundColor

@Preview
@Composable
fun CustomSection(
    header : String = "Customers Count",
    value : String = "12",
    textAlign: TextAlign = TextAlign.Center,
    bgColor: Color = backgroundColor
){
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = bgColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ){
            Text(text = header , fontSize = 18.sp, fontFamily = MediumFont)
            Text(text = value ,fontSize = 24.sp ,
                fontFamily = BoldFont, color = PrimaryColor,
                modifier = Modifier.fillMaxWidth() , textAlign = textAlign)
        }
    }
}



