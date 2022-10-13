package com.br.apimaker.startwarsapi.log

import org.springframework.stereotype.Component
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.nio.file.Paths
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Component
class LogManager {

    companion object {
        const val DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss"
    }

    fun info(message: String) {
        val log = "${dateTimeNowFormatted()} INFO $message"
        write(log)
    }

    fun error(message: String) {
        val log = "${dateTimeNowFormatted()} ERROR $message"
        write(log)
    }

    private fun write(message: String) {
        val logFile = File("${Paths.get("").toAbsolutePath()}/logfile.txt")

        if(!logFile.exists())
            logFile.createNewFile()

        val doNotResetFile = true
        FileOutputStream(logFile, doNotResetFile).apply { writeCsv(message) }
    }

    fun OutputStream.writeCsv(message: String) {
        val writer = bufferedWriter()
        writer.write(message)
        writer.newLine()
        writer.flush()
    }

    private fun dateTimeNowFormatted(): String? =
        DateTimeFormatter.ofPattern(DATE_TIME_PATTERN, Locale.ENGLISH).format(LocalDateTime.now())
}