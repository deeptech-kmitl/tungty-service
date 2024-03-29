package com.tungty.tungtyservice.DTO

data class ReqEditProfileDTO (
        var userId: String,
        var name:String,
        var surname: String,
        var password: String,
        var studentId: String,
        var faculty: String,
        var field: String,
        var year: String,
        var aboutMe: String?,
        var profileImg: String
        )