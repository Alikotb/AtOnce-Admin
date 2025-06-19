package com.example.atonce_admin.presentation.enums

import androidx.compose.ui.graphics.Color

enum class OrderStatesEnum(val value: String ,val title : String , val color : Color){

    ORDERED("New" , "New Orders" , Color(0xFF2A62FF)),
    PROCESSING("Processing", "Processing Orders" , Color.Magenta),
    SHIPPED("Shipped" , "Shipped Orders" , Color.Cyan),
    DELIVERED("Delivered", "Delivered Orders" , Color(0xFF00B259)),
    CANCELED("Canceled", "Canceled Orders" , Color(0xFFFF1111)),
    RETURNED("Returned", "Returned Orders" , Color(0xFFFE7E0F))
}