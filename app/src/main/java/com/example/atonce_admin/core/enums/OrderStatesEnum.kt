package com.example.atonce_admin.core.enums

import androidx.compose.ui.graphics.Color
import java.util.Locale

enum class OrderStatesEnum(val value: String ,
                           val arValue : String ,
                           val title : String ,
                           val arTitle : String ,
                           val color : Color){

    ORDERED("New" ,"جديد", "New Orders" , "الطلبات الجديدة" , Color(0xFF2A62FF)),
    PROCESSING("Processing","قيد التنفيذ", "Processing Orders" , "الطلبات قيد التنفيذ" , Color.Magenta),
    SHIPPED("Shipped","قيد التوصيل" , "Shipped Orders" , "الطلبات قيد التوصيل", Color.Cyan),
    DELIVERED("Delivered","مستلم", "Delivered Orders", "الطلبات المستلمة" , Color(0xFF00B259)),
    CANCELED("Canceled","ملغي", "Canceled Orders" , "الطلبات الملغية", Color(0xFFFF1111)),
    RETURNED("Returned","مرتجع", "Returned Orders", "الطلبات المرتجعة" , Color(0xFFFE7E0F));

    fun getLocalizedTitle(): String {
        return if (Locale.getDefault().language == "ar") arTitle else title
    }

    fun getLocalizedValue(): String {
        return if (Locale.getDefault().language == "ar") arValue else value
    }

}