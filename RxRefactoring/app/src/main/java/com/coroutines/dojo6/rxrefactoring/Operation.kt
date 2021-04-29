package com.coroutines.dojo6.rxrefactoring

import java.time.LocalTime
import java.util.*

fun getUser(): UserResponse {
    Thread.sleep(1500L)
    return UserResponse(UUID.randomUUID().toString(), "User-${LocalTime.now()}")
}