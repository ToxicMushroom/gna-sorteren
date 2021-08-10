package me.melijn.sorting.linear

import me.melijn.sorting.utils.getRandomStrings

/**
 * LSD (Radix) sort
 * Consider characters from right to left
 * Stable sort using dth character as key
 * Uses counting sort (a stable sort)
 **/
object LSDSort {


    private const val aCode = 'a'.code

    /** Requires strings of equal length: W **/
    fun sort(toSort: MutableList<String>): MutableList<String> {
        if (toSort.size <= 1) return toSort

        for (i in (toSort[0].length - 1) downTo 0) {
            val counter = Array(26) { 0 }

            for (el in toSort) counter[el[i].code - aCode]++
            for (j in 0 until counter.size - 1) counter[j + 1] += counter[j]
            val aux = Array(toSort.size) { "" }
            for (j in (toSort.size - 1) downTo 0) aux[counter[toSort[j][i].code - aCode]-- - 1] = toSort[j]
            for (j in aux.indices) toSort[j] = aux[j]
        }
        return toSort
    }

}

fun main() {
    println(LSDSort.sort(getRandomStrings(200, 10)))
}

// W: word length
// R: different characters
// Each word needs to be the same length or missing characters need to be replaced with some other letter
// ~7WN + 3WR
// ~N+R extra space