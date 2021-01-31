package com.acme.tour.utils.advice

import com.acme.tour.model.ErrorMessage
import com.acme.tour.utils.exception.PromotionNotFountException
import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.client.HttpStatusCodeException
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Controller advice serve como um filtro para tratarmos as exceções
 */
@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(JsonParseException::class)
    fun jsonFormatExceptionHandler(
        servletRequest: HttpServletRequest,
        servletResponse: HttpServletResponse,
        exception: Exception
    ) : ResponseEntity<ErrorMessage>{
        return ResponseEntity(
            ErrorMessage("Json Error", exception.message ?: "Inválid Json"),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(HttpStatusCodeException::class)
    fun statusCodeExceptionHandler(
        servletRequest: HttpServletRequest,
        servletResponse: HttpServletResponse,
        exception: Exception
    ) : ResponseEntity<ErrorMessage>{
        return ResponseEntity(
            ErrorMessage("Json Error", exception.message ?: "Inválid Json"),
            HttpStatus.MULTI_STATUS
        )
    }


    @ExceptionHandler(PromotionNotFountException::class)
    fun promotionNotFoundExceptionExceptionHandler(
        servletRequest: HttpServletRequest,
        servletResponse: HttpServletResponse,
        exception: Exception
    ) : ResponseEntity<ErrorMessage>{
        return ResponseEntity(
            ErrorMessage("Promoção não localizada", exception.message ?: ""),
            HttpStatus.NOT_FOUND
        )
    }
}