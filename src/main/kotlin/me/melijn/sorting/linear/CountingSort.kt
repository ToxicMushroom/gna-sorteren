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
        val count = Array(R + 1) { 0 }
        val aux = Array(toSort.size) { '0' }

        for (i in 0 until N) count[toSort[i].lowercaseChar().code - 'a'.code + 1]++
        for (r in 0 until R) count[r + 1] += count[r]
        for (i in 0 until N) aux[count[toSort[i].lowercaseChar().code - 'a'.code]++] = toSort[i]
        for (i in 0 until N) toSort[i] = aux[i]
        return toSort
    }

}

fun main() {
    println(
        CountingSort.sort(Names.map { it.first() }.toMutableList())
    )
}
// Uses 8N + 3R + 1 array accesses to sort N records whose keys are between 0 and R-1
// Uses extra space proportional to N + R
// Only useful if you have many duplicate keys!
// + Sorting people by age
// - Sorting people by birthday