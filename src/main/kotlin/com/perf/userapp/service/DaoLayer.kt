package com.perf.userapp.service

import com.perf.userapp.model.Author
import com.perf.userapp.model.Book
import org.springframework.data.mongodb.repository.MongoRepository

/*Spring does the implementation for us*/
interface AuthorDAO:MongoRepository<Author,String>
interface BookDAO:MongoRepository<Book,String>{
    fun findByAuthorId(id:String):List<Book>
    fun findByNameRegex(name:String):List<Book>
}