package com.acme.tour.controller

import com.acme.tour.model.ErrorMessage
import com.acme.tour.model.Promotion
import com.acme.tour.model.ResponseMessage
import com.acme.tour.services.service.PromotionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(value = ["promotions"])
class PromotionController {

    @Autowired
    lateinit var promotionService: PromotionService

    @RequestMapping(value = ["/say_hello"], method = [RequestMethod.GET])
    fun sayHello() = "Hello Spring"

    @GetMapping()
    fun getAll() : ResponseEntity<List<Promotion>> {
        val promotions = promotionService.getAll()
        val statusCode = if (promotions.isEmpty()) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(promotions, statusCode)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) : ResponseEntity<Any?> {
        val promotion = promotionService.getById(id)
        return if(promotion != null) ResponseEntity(promotion, HttpStatus.OK)
        else return ResponseEntity(
            ErrorMessage("Promoção não localizada", "A promoção com o id: $id não foi localizada"),
            HttpStatus.NOT_FOUND
        )
    }

    @GetMapping("/locate")
    fun getByLocal(@RequestParam(required = true) local: String) : ResponseEntity<List<Promotion>> {
        val promotions = promotionService.getByLocal(local)
        val statusCode = if (promotions.isEmpty()) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(promotions, statusCode)
    }

    @PostMapping("/create")
    fun create(@RequestBody promotion: Promotion) : ResponseEntity<Map<String, String>> {
        val message = mapOf("message" to promotionService.create(promotion))
        return ResponseEntity( message, HttpStatus.CREATED)
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<ResponseMessage> {
        lateinit var message: ResponseMessage
        lateinit var statusCode: HttpStatus
        if (promotionService.getById(id) != null ) {
            statusCode = HttpStatus.ACCEPTED
            message = ResponseMessage(
                message = promotionService.delete(id),
                code = HttpStatus.ACCEPTED.value(),
                date = Date()
            )
        } else {
            statusCode = HttpStatus.NOT_FOUND
            message = ResponseMessage(
                message = "Promoção não encontrada",
                code = HttpStatus.NOT_FOUND.value(),
                date = Date()
            )
        }
        return ResponseEntity(message, statusCode)
    }

    @PostMapping("/update")
    fun update(@RequestBody promotion: Promotion) : ResponseEntity<Unit> {
        val statusCode = if (promotionService.getById(promotion.id) != null ) {
            promotionService.update(promotion)
            HttpStatus.ACCEPTED
        } else {
            HttpStatus.NOT_FOUND
        }
        return ResponseEntity(Unit, statusCode)
    }
}