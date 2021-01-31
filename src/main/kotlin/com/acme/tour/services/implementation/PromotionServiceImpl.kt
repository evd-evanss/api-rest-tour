package com.acme.tour.services.implementation

import com.acme.tour.model.Promotion
import com.acme.tour.services.service.PromotionService
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class PromotionServiceImpl: PromotionService {
    companion object {

        val initialPromotions = listOf(
            Promotion(
                id = 1, description = "Viagem maravilhosa a Cancún",
                local = "Cancún",
                isAllInclusive = true,
                daysRemain = 7,
                price = 100.00
            ),
            Promotion(
                id = 2,
                description = "Tour pela Europa",
                local = "Europa",
                isAllInclusive = true,
                daysRemain = 7,
                price = 300.00
            ),
            Promotion(
                id = 3,
                description = "Viagem radical com rappel",
                local = "Nova Zelandia",
                isAllInclusive = true,
                daysRemain = 7,
                price = 1000.000
            ),
        )



        val promotions = ConcurrentHashMap<Long, Promotion>(initialPromotions.associateBy(Promotion::id))
    }

    override fun getAll() = promotions.values.toList()

    override fun getById(id: Long) = promotions[id]

    override fun getByLocal(local: String) =
        promotions.filter {
            it.value.local == local
        }.map(Map.Entry<Long, Promotion>::value).toList()

    override fun create(promotion: Promotion): String {
        return try {
            promotions[promotion.id] = promotion
            "Promoção inserida com sucesso!!!"
        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override fun delete(id: Long): String {
        return try {
            promotions.remove(id)
            "Promoção deletada com sucesso!!!"
        } catch (e: Exception) {
            e.message.toString()
        }
    }

    override fun update(promotion: Promotion): String {
        return try {
            promotions.remove(promotion.id)
            create(promotion)
            "Promoção ${promotion.description} \natualizada com sucesso!!!"
        } catch (e: Exception) {
            e.message.toString()
        }
    }
}