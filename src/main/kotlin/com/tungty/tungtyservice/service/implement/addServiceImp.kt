package com.tungty.tungtyservice.service.implement

import com.tungty.tungtyservice.service.addService
import org.springframework.stereotype.Service

@Service
class addServiceImp: addService {
    override fun addNumber(number: Int): String {
        return "" + (number+2)
    }
}