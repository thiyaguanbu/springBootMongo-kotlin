package com.perf.userapp.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate


@Document data class Author(@Id val id:String, val name:String, val birthDate:LocalDate)

@Document data class Book(@Id val isbn:String, val name:String, var author: Author, val publishedYear:Int)
