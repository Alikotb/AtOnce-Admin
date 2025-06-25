package com.example.atonce_admin.presentation.pharmacyOrders

import androidx.lifecycle.ViewModel
import com.example.atonce_admin.domain.usecase.GetPharmacyOrdersUseCase

class PharmacyOrdersViewModel(
    private val getPharmacyOrdersUseCase: GetPharmacyOrdersUseCase
) : ViewModel(){
}