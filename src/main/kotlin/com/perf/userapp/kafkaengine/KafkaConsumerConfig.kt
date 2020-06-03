package com.perf.userapp.kafkaengine

import com.perf.userapp.model.Book
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer
import java.util.*

@EnableKafka
@Configuration
class KafkaConsumerConfig{

    @Value(value = "\${kafka.bootstrapAddress}")
    private val bootstrapAddress: String? = null

    fun bookConsumerFactory(): ConsumerFactory<String?, Book?> {
        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress!!
        props[ConsumerConfig.GROUP_ID_CONFIG] = "welcome"
//        return DefaultKafkaConsumerFactory<Any?, Any?>(props, StringDeserializer(), JsonDeserializer<Any?>(Book::class.java))
        return DefaultKafkaConsumerFactory<String?, Book?>(props,StringDeserializer(), JsonDeserializer<Book>(Book::class.java))
    }

    @Bean
    fun bookKafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Book>? {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Book>()
        factory.consumerFactory = bookConsumerFactory()
        return factory
    }
}