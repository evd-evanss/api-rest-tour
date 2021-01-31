package com.acme.tour.model

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*

data class ResponseMessage(
    val message: String,
    val code: Int,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - HH:mm:ss")
    val date: Date
)