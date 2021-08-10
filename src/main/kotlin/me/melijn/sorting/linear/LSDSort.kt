package me.melijn.sorting.linear

import me.melijn.sorting.utils.RandomEqualStrings

// LSD (Radix) sort
object LSDSort {
    // Consider characters from right to left
    // Stable sort using dth character as key
    // Uses counting sort
    // ~ 7WN + 3WR + 1

    fun sort(toSort: MutableList<String>): MutableList<String> {
        if (toSort.size <= 1) return toSort

        for (i in (toSort[0].length - 1) downTo 0) {
            val counter = Array(26) { 0 }
            for (el in toSort)
                counter[el[i].code - 'a'.code]++
            for (j in 0 until counter.size - 2) counter[j + 1] += counter[j]
            val aux = Array(toSort.size) { "" }
            for (j in (toSort.size - 1) downTo 0) {
                aux[counter[toSort[j][i].code - 'a'.code]-- - 1] = toSort[j]
            }
            for (j in aux.indices) toSort[j] = aux[j]
        }
        return toSort
    }

}

fun main() {
    println(LSDSort.sort(RandomEqualStrings))
}

// W: word length
// R: different characters
// Each word needs to be the same length or missing characters need to be replaced with some other letter
// ~7WN + 3WR
// ~N+R extra space