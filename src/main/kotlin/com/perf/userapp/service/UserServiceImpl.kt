package com.perf.userapp.service

import com.perf.userapp.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query
import java.util.HashMap

class UserServiceImpl : UserService {

    @Autowired
    var mongoTemplate: MongoTemplate? = null

    override fun getAllUsers(): List<User?>? {
        return mongoTemplate!!.findAll(User::class.java)
    }

    override fun getUserById(userId: String?): User? {
        val query = Query()
        query.addCriteria(Criteria.where("userId").`is`(userId))
        return mongoTemplate!!.findOne(query, User::class.java)
    }

    override fun addNewUser(user: User?): User? {
        TODO("Not yet implemented")
    }

    override fun getAllUserSettings(userId: String?): Map<String?, String?>? {
        TODO("Not yet implemented")
    }

    override fun getUserSetting(userId: String?, key: String?): String? {
        TODO("Not yet implemented")
    }

    override fun addUserSetting(userId: String?, key: String?, value: String?): String? {
        TODO("Not yet implemented")
    }

    fun addNewUser(user: User): Any? {
        return mongoTemplate!!.save<Any>(user)
    }

//    override fun getAllUserSettings(userId: String?): Map<String?, String?>? {
//        val query = Query()
//        query.addCriteria(Criteria.where("userId").`is`(userId))
//        val user = mongoTemplate!!.findOne(query, User::class.java)
//        var userSetting: Map<String?, String?>? = HashMap()
//        userSetting = user.userSettings
//        return userSetting
//    }
//
//    fun getUserSetting(userId: String?, key: String): String? {
//        val query = Query()
//        query.fields().include("userSettings")
//        query.addCriteria(Criteria.where("userId").`is`(userId).andOperator(Criteria.where("userSettings.$key").exists(true)))
//        val user = mongoTemplate!!.findOne(query, User::class.java)
//        return user?.getUserSettings()?.get(key) ?: "User setting is not found"
//    }
//
//    override fun addUserSetting(userId: String?, key: String?, value: String?): String? {
//        val query = Query()
//        query.addCriteria(Criteria.where("userId").`is`(userId))
//        val user = mongoTemplate!!.findOne(query, User::class.java)
//        if (null != user) {
//            user.getUserSettings().put(key, value)
//            mongoTemplate!!.save<Any>(user)
//            return "Key added successfully"
//        }
//        return "User not found"
//    }
}