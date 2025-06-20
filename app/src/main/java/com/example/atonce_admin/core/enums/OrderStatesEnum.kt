package com.example.atonce_admin.core.enums

import androidx.compose.ui.graphics.Color
import java.util.Locale

enum class OrderStatesEnum(val value: String ,
                           val title : String ,
                           val arTitle : String ,
                           val color : Color){

    ORDERED("New" , "New Orders" , "الطلبات الجديدة" , Color(0xFF2A62FF)),
    PROCESSING("Processing", "Processing Orders" , "الطلبات قيد التنفيذ" , Color.Magenta),
    SHIPPED("Shipped" , "Shipped Orders" , "الطلبات قيد التوصيل", Color.Cyan),
    DELIVERED("Delivered", "Delivered Orders", "الطلبات المستلمة" , Color(0xFF00B259)),
    CANCELED("Canceled", "Canceled Orders" , "الطلبات الملغية", Color(0xFFFF1111)),
    RETURNED("Returned", "Returned Orders", "الطلبات المرتجعة" , Color(0xFFFE7E0F));

    fun getLocalizedTitle(): String {
        return if (Locale.getDefault().language == "ar") arTitle else title
    }

}