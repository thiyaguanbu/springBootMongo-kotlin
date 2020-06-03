package com.perf.userapp.messageservice

import com.perf.userapp.model.Book
import com.perf.userapp.service.BookService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.util.concurrent.CountDownLatch

@Service
class MessageConsumer(private val bookService: BookService){

    val latch = CountDownLatch(3)

    val bookLatch = CountDownLatch(1)

    @KafkaListener(topics = ["\${book.topic.name}"], containerFactory = "bookKafkaListenerContainerFactory")
    fun bookListener(book: Book) {
        println("Recieved message: $book")
        val savedBook: Book =  bookService.insert(book)
        println("Book details persisted in DB: $savedBook")
        bookLatch.countDown()
    }
}