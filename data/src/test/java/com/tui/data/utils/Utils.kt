package com.tui.data.utils

import java.io.FileNotFoundException
import java.net.URL
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

class Utils {

    companion object {
        fun readFile(path: String): String {
            val content: URL? = ClassLoader.getSystemResource(path)
            return content?.readText()
                ?: throw FileNotFoundException("file path: $path was not found")
        }

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