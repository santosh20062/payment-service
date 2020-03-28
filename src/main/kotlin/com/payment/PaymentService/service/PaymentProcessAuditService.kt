package com.payment.PaymentService.service

import com.payment.PaymentService.dao.PaymentAuditRepository
import com.payment.PaymentService.model.PaymentAudit
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service("paymentProcessAuditService")
class PaymentProcessAuditService(@Autowired val paymentAuditRepository : PaymentAuditRepository) {

    fun createPaymentAudit(orderAudit :  PaymentAudit): Mono<PaymentAudit> {

        return paymentAuditRepository.save(orderAudit)
    }

    fun findAllOrderAudit(): Flux<PaymentAudit> {
        return paymentAuditRepository.findAll()
    }

    fun getById(id: String): Mono<ResponseEntity<PaymentAudit>> {
        return paymentAuditRepository.findById(id)
                .map({ request -> ResponseEntity.ok<PaymentAudit>(request) })
                .defaultIfEmpty(ResponseEntity.notFound().build<PaymentAudit>())
    }


    fun getByOrderId(paymentId: String): Flux<PaymentAudit> {
        return paymentAuditRepository.findByPaymentId(paymentId)
    }
}