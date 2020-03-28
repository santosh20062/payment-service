package com.payment.PaymentService.dao

import com.payment.PaymentService.model.Payment
import com.payment.PaymentService.model.PaymentAudit
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux


@Repository("paymentAuditRepository")
interface PaymentAuditRepository : ReactiveCrudRepository<PaymentAudit,String> {

    @Query("{orderId: ?0 }")
    fun findByPaymentId(orderId: String ) : Flux<PaymentAudit>
}