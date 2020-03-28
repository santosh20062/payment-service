package com.payment.PaymentService.controller

import com.payment.PaymentService.model.Payment
import com.payment.PaymentService.service.PaymentProcessService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/payments")
class PaymentController(@Autowired val paymentProcessService: PaymentProcessService) {

    @GetMapping
    fun getAll() : Flux<Payment> {
        return paymentProcessService.findAll()
    }






    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    fun createBooks(@RequestBody payment: Payment): Mono<Payment> {

        return paymentProcessService.createPayment(payment)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable(value = "id") id: String): Mono<ResponseEntity<Payment>> {

        return paymentProcessService.getById(id)
    }
}