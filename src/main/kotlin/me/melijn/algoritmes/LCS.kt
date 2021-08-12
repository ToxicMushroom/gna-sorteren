package me.melijn.algoritmes

import kotlin.math.max

class LCS {

    fun findLongestCommonSubsequence(string1: StringSlice, string2: StringSlice): String {
        val table = mutableMapOf<Coordinate, Int>()
        // Save all mutual characters into a 2d map
        for (y in 0 until string2.totalLength) {
            val s2 = string2.charAt(y)
            for (x in 0 until string1.totalLength) {
                val s1 = string1.charAt(x)
                val newCellValue = if (s2 == s1) {
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
        var (x, y) = string1.totalLength - 1 to string2.totalLength - 1
        var construct = ""
        while (x != -1 && y != -1) {
            val current = table[Coordinate(x, y)] ?: 0
            val parent = table[Coordinate(x - 1, y - 1)] ?: 0
            if (current != parent) {
                construct = string2.charAt(y) + construct
                x--
                y--
            } else {
                y--
            }
        }

        return construct
    }
}

fun main() {
    val res = LCS().findLongestCommonSubsequence(StringSlice("baab"), StringSlice("bacb"))
    println(res)
}

class StringSlice(var value: String, var start: Int = 0, var end: Int = value.length - 1) : Iterable<Char> {
    val totalLength = value.length
    val length = end - start

    override fun iterator(): Iterator<Char> {
        var position = start
        return object : CharIterator() {
            override fun hasNext(): Boolean {
                return position < end
            }

            override fun nextChar(): Char {
                return value[++position]
            }
        }
    }

    fun charAt(pos: Int): Char = value[start + pos]
}

data class Coordinate(val x: Int, val y: Int)