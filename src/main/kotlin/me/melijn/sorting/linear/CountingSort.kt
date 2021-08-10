package me.melijn.sorting.linear

import me.melijn.sorting.utils.Names

/**
 * Also goes by: Key-indexed sorting
 */
object CountingSort {

    fun sort(toSort: MutableList<Char>): MutableList<Char> {
        if (toSort.size <= 1) return toSort

        val N = toSort.size
        val R = 26
        val count = Array(R + 1) { 0 } // [0, 0, 0, 0]
        val aux = Array(toSort.size) { '0' }

        for (i in 0 until N) count[getValuesCountingIndex(toSort, i) + 1]++ // Populate the array with character counts [0, 4, 5, 3]
        for (r in 0 until R) count[r + 1] += count[r] // Make the counts start counting on top of another (cumulative) [0, 4, 9, 12]
        for (i in 0 until N) aux[count[getValuesCountingIndex(toSort, i)]++] = toSort[i] // try to understand this by maybe writing it on paper :)
        for (i in 0 until N) toSort[i] = aux[i] // propagate aux array back into toSort
        return toSort
    }

    private fun getValuesCountingIndex(toSort: MutableList<Char>, i: Int) =
        toSort[i].lowercaseChar().code - 'a'.code

}

fun main() {
    println(
        CountingSort.sort(Names.map { it.first() }.toMutableList())
    )
}
// Uses 8N + 3R + 1 array accesses to sort N records whose keys are between 0 and R-1
// Uses extra space proportional to N + R
// Only useful if you have many duplicate keys!
//   good for sorting people by age
//   not good for sorting people by birthday
