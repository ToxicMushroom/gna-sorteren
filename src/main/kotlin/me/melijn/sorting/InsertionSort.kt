package me.melijn.sorting

object InsertionSort {

    fun <T : Comparable<T>> sort(toSort: MutableList<T>): MutableList<T> {
        if (toSort.size <= 1) return toSort
        for (i in 1 until toSort.size) {
            var j = i
            while (j > 0 && toSort[j - 1] > toSort[j]) {
                toSort.swap(j - 1, j--)
            }
        }
        return toSort
    }
}

fun main() {
    val arr = mutableListOf(5, 2, 3, 0, 56, 7, 5, 7, 9)
    println(InsertionSort.sort(arr))
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
// Half of the elements on the left are larger
// Worst case /2
// Compares, Swaps | ~n^2/4

/** Each element is max. 3 positions away from its final location **/
// ~5n/2