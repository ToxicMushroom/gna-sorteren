package me.melijn.sorting

import me.melijn.sorting.model.SortingAlgorithm

class MergeSort : SortingAlgorithm(false) {

    override fun <T : Comparable<T>> internalSort(toSort: MutableList<T>): List<T> {
        if (toSort.size <= 1) return toSort
        return sortRange(toSort, 0, toSort.size)
    }

    private fun <T : Comparable<T>> sortRange(toSort: MutableList<T>, lo: Int, hi: Int): List<T> {
        if ((hi - lo) == 1) return listOf(toSort[lo])
        val split = lo + ((hi - lo) / 2)
        val part1 = sortRange(toSort, lo, split)
        val part2 = sortRange(toSort, split, hi)

        val merged = ArrayList<T>(part1.size + part2.size)
        merge(part1, part2, merged)
        return merged
    }

    private fun <T : Comparable<T>> merge(
        part1: List<T>,
        part2: List<T>,
        merged: ArrayList<T>
    ) {
        var (i, j) = 0 to 0
        while (i != part1.size || j != part2.size) {
            when {
                i == part1.size -> {
                    merged.addAll(part2.subList(j, part2.size))
                    j = part2.size
                }
                j == part2.size -> {
                    merged.addAll(part1.subList(i, part1.size))
                    i = part1.size
                }
                else -> {
                    val (el1, el2) = part1[i] to part2[j]
                    val smallest = if (el1 < el2) {
                        i++
                        el1
                    } else {
                        j++
                        el2
                    }
                    compares++
                    merged.add(smallest)
                }
            }
        }
    }
}

fun main() {
    val arr = mutableListOf(5, 2, 3, 0, 56, 7, 5, 7, 9)
    println(MergeSort().sort(arr))
}

/** BEST CASE **/
// The left parts while merging are all smaller than the right parts, thus the right part doesn't require compares and can be quickly copied
// log^2(n) is the amount of times you can divide the list in 2 before reaching 1/2
// (n-1)/2 + (n-2)/2 + (n-4)/2 ...
// n/2 * log^2(n) - (1/2 + 1 + 2 + 4...)
// n/2 * log^2(n) - n/2
// ~ n/2 * log^2(n)


/** WORST CASE **/
// n-1 + n-2 + n-4 + n-8 ...
// n * log^2(n) - (1 + 2 + 4 + 8 ...)
// n * log^2(n) - n
// ~ n * log^2(n)


