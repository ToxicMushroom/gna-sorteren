package me.melijn.sorting

object MergeSort {

    fun <T : Comparable<T>> sort(toSort: MutableList<T>): List<T> {
        if (toSort.size <= 1) return toSort
        return internalSort(toSort, 0, toSort.size)
    }

    private fun <T : Comparable<T>> internalSort(toSort: MutableList<T>, lo: Int, hi: Int): List<T> {
        if ((hi - lo) == 1) return listOf(toSort[lo])
        val split = lo + ((hi - lo) / 2)
        val part1 = internalSort(toSort, lo, split)
        val part2 = internalSort(toSort, split, hi)

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
                i == part1.size -> merged.add(part2[j++])
                j == part2.size -> merged.add(part1[i++])
                else -> {
                    val (el1, el2) = part1[i] to part2[j]
                    val smallest = if (el1 < el2) {
                        i++
                        el1
                    } else {
                        j++
                        el2
                    }
                    merged.add(smallest)
                }
            }
        }
    }
}

fun main() {
    val arr = mutableListOf(5, 2, 3, 0, 56, 7, 5, 7, 9)
    println(MergeSort.sort(arr))
}
// LE = the amount of larger elements on the left
// Compares, for each element 1 + LE
// Swaps, for each element LE

/** BEST CASE LE **/
// All the elements on the left are smaller
// Compares, 1+1+1...+1 = n-1
// Swaps, 0

/** WORST CASE LE **/
// All the elements on the left are larger
// Compares, 1 + 2 + ... + n-2 + n-1 = n*(n-1)/2 | ~n^2/2
// Swaps, 1 + 2 + ... + n-2 + n-1 | ~n^2/2

/** AVERAGE CASE LE **/
// Half of the elements on the left are largerx
// Compares, Swaps | ~n^2/4

/** Each element is max. 3 positions away from its final location **/
// ~5n/2