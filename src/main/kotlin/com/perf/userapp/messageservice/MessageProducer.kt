package com.perf.userapp.messageservice


import com.perf.userapp.model.Book
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.util.concurrent.ListenableFutureCallback

@Service
class MessageProducer {

    @Autowired
    private var bookKafkaTemplate: KafkaTemplate<String, Book>? = null

    @Value(value = "\${book.topic.name}")
    private val bookTopicName: String? = null

    fun sendMessage(book: Book) {
        val future = bookKafkaTemplate!!.send(bookTopicName!!, book)
        future.addCallback(object : ListenableFutureCallback<SendResult<String?, Book?>?> {
            override fun onSuccess(result: SendResult<String?, Book?>?) {
                println("Sent message=[$book] with offset=[" + result!!.recordMetadata
                        .offset() + "]")
            }

            override fun onFailure(ex: Throwable) {
                println("Unable to send message=[" + book.author + "] due to : " + ex.message)
            }
        })
    }

    fun sendBookDetails(book: Book) : String {
        println("Inside sendBookDetails : Book ${book.name}")
        bookKafkaTemplate!!.send(bookTopicName!!, book)
        return "success"
    }
}