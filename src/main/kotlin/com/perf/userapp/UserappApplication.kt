package com.perf.userapp


import com.perf.userapp.model.Author
import com.perf.userapp.model.Book
import com.perf.userapp.repository.UserMongoRepository
import com.perf.userapp.service.AuthorDAO
import com.perf.userapp.service.BookDAO
import com.z.bookbackend.util.toLocalDate
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class UserappApplication(private val userMongoRepository : UserMongoRepository, private val bookDAO: BookDAO, private val authorDAO: AuthorDAO): ApplicationRunner {
	/* This will run after springboot full load*/
	override fun run(args: ApplicationArguments?) {
		if(bookDAO.count()<5) this.createBooks()
	}


	private fun createUsers(){
		
	}
	private fun createBooks(){
		this.cleanCollections()
		val george = authorDAO.insert(Author(id = "1000", name = "George R. R. Martin",birthDate =  "20-09-1948".toLocalDate()))
		val tolkien = authorDAO.insert(Author(id = "1001", name = "J. R. R. Tolkien", birthDate = "03-01-1892".toLocalDate()))

		val books = listOf(
				Book(isbn = "9780553573428",name = "A Storm of Swords", publishedYear = 2011, author = george),
				Book(isbn = "9780553579901", name = "A clash of kings", publishedYear = 2005, author = george),
				Book(isbn = "9780618260553", name = "The Return of the King", publishedYear = 2002, author = tolkien)
		)
		books.forEach { println(it.toString()) }
		bookDAO.insert(books)
	}

	private fun cleanCollections(){
		authorDAO.deleteAll()
		bookDAO.deleteAll()

	}
}
fun main(args: Array<String>) {
	SpringApplication.run(UserappApplication::class.java, *args)
}