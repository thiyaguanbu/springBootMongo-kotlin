package com.perf.userapp.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class User(

        @Id
        val _id: String,
        val userId: String,
        val name: String,
        val jobTitle: String,
        val goalPeriod: String,
        val role: String,
        val goalType: String,
        val creationDate: Date = Date()

)