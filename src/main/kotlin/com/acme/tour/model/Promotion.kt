package com.acme.tour.model

data class Promotion(
    val id: Long,
    val description: String,
    val local: String,
    val isAllInclusive: Boolean,
    val daysRemain: Int,
    val price: Number
)