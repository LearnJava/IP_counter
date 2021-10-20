package ru.konstantin

import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.concurrent.atomic.AtomicLong

class RunMe {
    fun getUniqIPs(path: String?) {
        var uniqIPs = mutableListOf<MyIp>()
        val allIpCounter = AtomicLong()
        var counter: Int = 1

        var uniqIpSet = mutableSetOf<MyIp>()

        var pathToFile = ru.konstantin.createTempFile(counter, "D:\\Temp\\ip_addresses\\test")

        try {
            val sourceLine = Files.lines(Paths.get(path))
            sourceLine.forEach { ip: String ->
                val temp = ip.split(".")
                uniqIpSet.add(MyIp(temp[0].toUByte(), temp[1].toUByte(), temp[2].toUByte(), temp[3].toUByte()))
//                uniqIPs.add(MyIp(temp[0].toUByte(), temp[1].toUByte(), temp[2].toUByte(), temp[3].toUByte()))
                if (uniqIpSet.size % 10_000_000 == 0) {
//                    thread {
//                        println(uniqIPs.size)
//                        addStringToFile(pathToFile, uniqIPs)
//                    }
                    println("Всего прочитали IP адресов " + uniqIpSet.size)
//                    pathToFile = ru.konstantin.createTempFile(counter, "D:\\Temp\\ip_addresses\\test")
//                    uniqIPs = mutableListOf<MyIp>()
                }
                allIpCounter.getAndIncrement()
                counter++
            }
            println("Всего IP адресов $allIpCounter")
            println("Всего IP адресов ${uniqIpSet.count()}")
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}

fun createTempFile(prefix: Int, path: String): String {
    val uuid = UUID.randomUUID()
    val randomUUIDString = uuid.toString()
    val file = File("$path/${prefix}_$randomUUIDString.txt")
    file.createNewFile()
    return file.absoluteFile.toString()
}

@Synchronized
fun addStringToFile(path: String, ipLists: MutableList<MyIp>) {

//    try {
        FileWriter(path, false).use { writer ->
            // запись всей строки
            val text = ipLists.joinToString("\n") { it.toString() }
            writer.write(text)
            // запись по символам
            writer.flush()
        }
//    } catch (ex: IOException) {
//        println(ex.message)
//    }
}

fun main() {
    RunMe().getUniqIPs("D:\\Temp\\ip_addresses\\ip_addresses")
    println("The end")
}
