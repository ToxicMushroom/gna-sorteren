package me.melijn.sorting.comparing

import me.melijn.sorting.model.SortingAlgorithm
import me.melijn.sorting.utils.getRandomIntList
import java.util.*

class HeapSort : SortingAlgorithm(false) {

    override fun <T : Comparable<T>> internalSort(toSort: MutableList<T>): List<T> {
        val queue = PriorityQueue(toSort) // TODO: Own priorityqueue :p
        for (el in toSort.indices) {
            val max = queue.poll()
            toSort[el] = max
        }
        return toSort
    }

}

fun main() {
    val list = getRandomIntList(1000, 100..500)
    HeapSort().sort(list)
    println(list)
}

/** Time complexity **/
// n log^2 (n) | 2n to build the heap in the priorityqueue 2n is best case
// 2log^2 (n) + 2log^2 (n-1) + 2log^2 (n-2) + 2log^2 (n-3) ... = sterling benadering
// ~2n*log^2(n)
// In practice heapsort isn't used often because for big arrays your ram is jumping around and can't do good caching ect.