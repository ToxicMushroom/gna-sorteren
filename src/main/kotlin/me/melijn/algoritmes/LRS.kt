package me.melijn.algoritmes

import kotlin.math.max

class LRS {
    fun findLRS(string: String):String {
        val table = mutableMapOf<Coordinate, Int>()
        for (y in string.indices) {
            val s2 = string[y]
            for (x in string.indices) {
                val s1 = string[x]
                val newCellValue = if (s2 == s1 && x != y) {
                    table.getOrDefault(Coordinate(x - 1, y - 1), 0) + 1
                } else {
                    max(
                        table.getOrDefault(Coordinate(x - 1, y), 0),
                        table.getOrDefault(Coordinate(x, y - 1), 0)
                    )
                }
                table[Coordinate(x, y)] = newCellValue
            }
        }

        // Go back to find an optimal solution
        var (x, y) = string.length - 1 to string.length - 1
        var construct = ""
        while (x != -1 && y != -1) {
            val current = table[Coordinate(x, y)] ?: 0
            val parent = table[Coordinate(x - 1, y - 1)] ?: 0
            if (current != parent) {
                construct = string[y] + construct
                x--
                y--
            } else {
                y--
            }
        }

        return construct
    }
}

fun main(args: Array<String>) {
    val str = "BBAEACDD"
    println(
        "The length of the largest subsequence that"
                + " repeats itself is : " + LRS().findLRS(str)
    )
}