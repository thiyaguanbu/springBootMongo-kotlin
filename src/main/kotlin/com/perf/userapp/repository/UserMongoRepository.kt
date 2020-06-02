package com.perf.userapp.repository

import com.perf.userapp.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserMongoRepository: MongoRepository<User, String>