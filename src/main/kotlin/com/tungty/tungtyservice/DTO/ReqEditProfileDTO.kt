package com.tungty.tungtyservice.dto

data class ReqEditProfileDTO (
    var userId: String,
    var name:String,
    var surname: String,
    var password: String,
    var studentId: String,
    var faculty: String,
    var field: String,
    var year: Int,
    var profileImg: String
        )