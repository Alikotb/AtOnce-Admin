package com.example.atonce_admin.core.enums

import androidx.compose.ui.graphics.Color
import java.util.Locale

enum class OrderStatesEnum(
    val value: String,
    val arValue: String,
    val title: String,
    val arTitle: String,
    val color: Color, val id: Int
) {

    ORDERED("New", "جديد", "New Orders", "الطلبات الجديدة", Color(0xFF2A62FF), 0),
    PROCESSING(
        "Preparing",
        "قيد التنفيذ",
        "Processing Orders",
        "الطلبات قيد التنفيذ",
        Color.Magenta,4
    ),
    SHIPPED("Delivering", "قيد التوصيل", "Shipped Orders", "الطلبات قيد التوصيل", Color.Cyan,5),
    DELIVERED("Delivered", "مستلم", "Delivered Orders", "الطلبات المستلمة", Color(0xFF00B259), 1),
    CANCELED("Canceled", "ملغي", "Canceled Orders", "الطلبات الملغية", Color(0xFFFF1111), 2),
    RETURNED("Returned", "مرتجع", "Returned Orders", "الطلبات المرتجعة", Color(0xFFFE7E0F), 3);

    fun getLocalizedTitle(): String {
        return if (Locale.getDefault().language == "ar") arTitle else title
    }

    fun getLocalizedValue(): String {
        return if (Locale.getDefault().language == "ar") arValue else value
    }

    companion object {
        fun fromName(name: String): OrderStatesEnum {
            return when (name) {
                "Ordered" -> ORDERED
                "Preparing" -> PROCESSING
                "Delivering" -> SHIPPED
                "Delivered" -> DELIVERED
                "Cancelled" -> CANCELED
                "Returned" -> RETURNED
                else -> ORDERED
            }
        }
    }

}