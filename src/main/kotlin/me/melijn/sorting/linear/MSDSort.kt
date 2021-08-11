package me.melijn.sorting.linear

import me.melijn.sorting.utils.LoremIpsum
import me.melijn.sorting.utils.isSorted

class MSDSort {

    val radix = 26
    val offset = 'a'.code

    fun charAt(text: String, depth: Int): Int = (text.getOrNull(depth)?.code?.minus(offset)) ?: -1

    fun sort(toSort: MutableList<String>, low: Int, high: Int, depth: Int) {
        if (high <= low) return

        val counter = Array(radix + 2) { 0 } // [0, 2, 3, 4]
        val aux = Array(high - low + 1) { "-" }
        for (i in low..high) counter[charAt(toSort[i], depth) + 2]++ // generate char counts
        for (r in 0..radix) counter[r + 1] += counter[r] // transform counts into indices
        for (i in low..high) {
            aux[counter[charAt(toSort[i], depth) + 1]++] = toSort[i]
        }
        for (i in low..high) toSort[i] = aux[i - low]

        for (r in 0 until radix) {
            sort(toSort, low + counter[r], low + counter[r + 1] - 1, depth + 1)
        }
    }
}

fun main() {
    val toSort = LoremIpsum.map { it.lowercase() }.toMutableList()
    MSDSort().sort(toSort, 0, toSort.size - 1, 0)
    println(toSort.joinToString())
    println(isSorted(toSort))
}






