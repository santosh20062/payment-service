package com.payment.PaymentService.service

import com.payment.PaymentService.dao.PaymentRepository
import com.payment.PaymentService.model.Payment
import com.payment.PaymentService.model.PaymentAudit

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.LocalDateTime


@Service("paymentProcessService")
class PaymentProcessService(@Autowired val paymentRepository: PaymentRepository,
                            @Autowired val paymentProcessAuditService: PaymentProcessAuditService) {

    fun findAll(): Flux<Payment> {
        return paymentRepository.findAll()
    }

    fun createPayment(payment: Payment): Mono<Payment> {

        println("payment :"+payment)
        var paymentAudit: PaymentAudit = PaymentAudit(null, null, payment.orderName, payment.orderType, payment.orderStatus, payment.paymentMethod, payment.paymentStatus, payment.description, payment.price, payment.quantity)
        paymentAudit.priceNew = payment.price;
        paymentAudit.quantityNew = payment.quantity
        paymentAudit.createdBy = "Admin"
        paymentAudit.status = "Created"
        paymentAudit.createdDate = LocalDateTime.now()
        paymentAudit.updatedBy = "Admin"


       // producer.sendMessage(orderAudit)
       println("paymentAudit  :"+paymentAudit)
        return paymentProcessAuditService.createPaymentAudit(paymentAudit)
                .flatMap { paymentRepository.save(payment) }

        //return paymentRepository.save(payment)


    }


    fun getById(id: String): Mono<ResponseEntity<Payment>> {
        return paymentRepository.findById(id)
                .map({ request -> ResponseEntity.ok<Payment>(request) })
                .defaultIfEmpty(ResponseEntity.notFound().build<Payment>())
    }
}