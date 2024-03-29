package com.tungty.tungtyservice.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

// User Entity

@Document(collection = "UserCollection")
data class UserEntity(
        @Id
        val userId: String,
        var name: String,
        var surname: String,
        @Indexed(unique = true)
        val username: String,
        var password: String,
        @Indexed(unique = true)
        var studentId: String,
        var faculty: String,
        var field: String,
        var year: String,
        var aboutMe: String?,
        var profileImg: String,
        val createDateTime: String,
        var updateDateTime: String
)