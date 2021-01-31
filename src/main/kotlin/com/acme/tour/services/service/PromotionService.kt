package com.acme.tour.services.service

import com.acme.tour.model.Promotion

interface PromotionService {

    fun getAll():  List<Promotion>
    fun getById(id: Long): Promotion?
    fun getByLocal(local: String): List<Promotion>
    fun create(promotion: Promotion): String
    fun delete(id: Long): String
    fun update(promotion: Promotion): String
}