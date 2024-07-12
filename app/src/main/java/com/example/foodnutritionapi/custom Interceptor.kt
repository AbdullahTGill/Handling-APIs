//package com.example.foodnutritionapi.com.example.foodnutritionapi
//
//import android.content.Context
//import okhttp3.Interceptor
//import okhttp3.Response
//import java.io.File
//import java.io.FileWriter
//import java.io.IOException
//import java.text.SimpleDateFormat
//import java.util.Date
//import java.util.Locale
//
//class FileLoggingInterceptor(private val context: Context, private val sessionId: String) : Interceptor {
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val request = chain.request()
//        val t1 = System.nanoTime()
//
//        val requestLog = StringBuilder()
//        requestLog.append("Sending request ${request.url} on ${chain.connection()}\n${request.headers}")
//
//        val response = chain.proceed(request)
//        val t2 = System.nanoTime()
//
//        val responseLog = StringBuilder()
//        responseLog.append("Received response for ${response.request.url} in ${(t2 - t1) / 1e6}ms\n${response.headers}")
//
//        val responseBody = response.peekBody(Long.MAX_VALUE)
//        responseLog.append(responseBody.string())
//
//        writeLogToFile(requestLog.toString(), responseLog.toString())
//
//        return response
//    }
//
//    private fun writeLogToFile(requestLog: String, responseLog: String) {
//        val directory = File(context.filesDir, "debug/network/session/$sessionId")
//        if (!directory.exists()) {
//            directory.mkdirs()
//        }
//
//        val logFile = File(directory, "network_log_${getCurrentTimeStamp()}.json")
//        try {
//            val writer = FileWriter(logFile, true)
//            writer.append("{\n")
//            writer.append("\"request\": \"$requestLog\",\n")
//            writer.append("\"response\": \"$responseLog\"\n")
//            writer.append("},\n")
//            writer.flush()
//            writer.close()
//        } catch (e: IOException) {
//            e.printStackTrace()
//        }
//    }
//
//    private fun getCurrentTimeStamp(): String {
//        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
//        return sdf.format(Date())
//    }
//}
