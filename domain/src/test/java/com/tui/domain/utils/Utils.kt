package com.tui.domain.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

class Utils {

    companion object {
        fun stringToDate(date: String): Date {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
            format.timeZone = TimeZone.getTimeZone("UTC")
            try {
                return format.parse(date)!!
            } catch (e: Exception) {
                throw e
            }
        }
    }

}