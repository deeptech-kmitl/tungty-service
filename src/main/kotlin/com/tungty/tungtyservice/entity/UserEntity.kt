package com.tungty.tungtyservice.entity

// User Entity
@Entity
data class UserEntity(
        @Id
        val userId: String,
        val name: String,
        val surname: String,
        val password: String,
        val studentId: String,
        val faculty: String,
        val field: String,
        val year: Int,
        val profileImg: String,
        val createDateTime: Date,
        val updateDateTime: Date
)