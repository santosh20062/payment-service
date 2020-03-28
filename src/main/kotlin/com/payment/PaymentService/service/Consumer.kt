package com.payment.PaymentService.service

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper

import com.fasterxml.jackson.databind.util.StdDateFormat
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

import com.payment.PaymentService.model.Payment

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

import java.io.IOException
import java.time.LocalDateTime


@Component
class Consumer(@Autowired
        val paymentProcessService: PaymentProcessService) {


    @KafkaListener(topics = ["order-topic"], groupId = "group_id")
    @Throws(IOException::class)
    fun consume(message: String) : String{

        println(LocalDateTime.now())
        println(String.format("#### -> Consumed message -> %s", message))

        val jsonMapper = ObjectMapper().apply {
            registerKotlinModule()
            disable(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS)
            setDateFormat(StdDateFormat())
        }

        val node: JsonNode = jsonMapper.readTree(message)
        println("orderAuditString :"+node)
        var payment : Payment = Payment(orderName = node["orderName"].asText(),orderStatus = node["orderStatus"].asText() ,description = node["description"].asText()
        ,price = node["price"].asDouble(),quantity = node["quantity"].asLong())


        paymentProcessService.createPayment(payment).subscribe()
        return "Consumed Message"
    }

}