package me.melijn.sorting

object QuickSort {

    fun <T : Comparable<T>> sort(list: MutableList<T>): MutableList<T> {
        sort(list, 0, list.size - 1)
        return list
    }

    private fun <T : Comparable<T>> sort(list: MutableList<T>, low: Int, high: Int) {
        if (high <= low) return
        val partitionIndex = partition(list, low, high)
        sort(list, low, partitionIndex - 1)
        sort(list, partitionIndex + 1, high)
    }

    private fun <T : Comparable<T>> partition(list: MutableList<T>, low: Int, high: Int): Int {
        var (i, j) = low to high + 1
        val part = list[low]
        while (true) {
            while (list[++i] < part) if (i == high) break
            while (part < list[--j]) if (j == low) break
            if (i >= j) break
            list.swap(i, j)
        }
        list.swap(low, j)
        return j
    }
}


fun main() {
    val arr = mutableListOf(5, 2, 3, 0, 56, 7, 5, 7, 9)
    println(QuickSort.sort(arr))
}
// LE = the amount of larger elements on the left
// Compares, for each element 1 + LE
// Swaps, for each element LE

/** BEST CASE LE **/
// - partition takes n compares
// - split is always balanced
// T(n) = 2T(n/2) + paritioning(n)
// T(n) = 2T(n/2) + n
// T(n) = 4T(n/4) + n + n
// ~ n log^2(n)

/** WORST CASE LE **/
// Partition element causes 0 Elements in one half and n-1 in the other half for each partition
// T(n) = T(n-1) + partitioning(n)
//      = T(n-2) + n-1

/** AVERAGE CASE **/
// See calculation in book
// ~ 2n*log^e(2)*log^2(n) = ~ 1,39 * n * log^2(n)
// notation I used is log^base(value)

/** Each element is max. 3 positions away from its final location **/
// ~5n/2