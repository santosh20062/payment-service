package com.payment.PaymentService.dao

import com.payment.PaymentService.model.Payment
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository


@Repository("paymentRepository")
interface PaymentRepository : ReactiveCrudRepository<Payment,String> {


}