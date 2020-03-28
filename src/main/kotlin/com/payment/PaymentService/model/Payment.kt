package com.payment.PaymentService.model


import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable

@Document
data class Payment(
                   @Id val id : String ?= null,



                   var orderId: String ?=null,
                   var orderName: String?=null,

                   var orderType: String?=null,

                   var orderStatus : String ?=null,


                   var paymentStatus : String ? = null,

                   var paymentMethod : String?=null,

                   var description: String?=null,


                   var price:  Double?=null,

                   var quantity: Long?=null) {
}