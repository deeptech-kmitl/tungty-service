package com.tungty.tungtyservice.dto

data class ResponseGetMyChatDTO(
    var errMessage: String?,
    var chatIdList: List<String>
)
