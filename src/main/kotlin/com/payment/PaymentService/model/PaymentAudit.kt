package com.payment.PaymentService.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.annotation.*
import org.springframework.data.mongodb.core.mapping.Document
import java.io.Serializable
import java.time.LocalDateTime

@Document
data class PaymentAudit(@JsonProperty
                        @Id val id : String ?= null,


                        @JsonProperty
                        var orderId: String ?,
                        var orderName: String?,
                        @JsonProperty
                        var orderType: String?,
                        @JsonProperty
                        var orderStatus : String?,

                        @JsonProperty
                        var paymentStatus : String?,
                        @JsonProperty
                        var paymentMethod : String?,
                        @JsonProperty
                        var description: String?,
                        @JsonProperty

                        var price:  Double?,
                        @JsonProperty
                        var quantity: Long?) : Serializable {


    @JsonProperty
    var status : String? =  null

    @JsonProperty
    var priceNew: Double? = null

    @JsonProperty
    var quantityNew : Long? = null



    @CreatedBy
    @JsonProperty
    var createdBy: String? = null


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss.SSS")
    @JsonProperty
    @CreatedDate

    var createdDate: LocalDateTime? = null



    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss.SSS")
    @JsonProperty
    @LastModifiedDate
    var lastModifiedDate: LocalDateTime? = null

    @JsonProperty
    @LastModifiedBy
    // @Column(name = "updated_by")

    var updatedBy: String? = null
}