package me.melijn.sorting

object SelectionSort {

    fun <T : Comparable<T>> sort(toSort: MutableList<T>): MutableList<T> {
        for (i in toSort.indices) {
            var min = i
            for (j in i until toSort.size) {
                if (toSort[min] > toSort[j]) {
                    min = j
                }
            }
            if (min != i) toSort.swap(i, min)
        }
        return toSort
    }
}

fun main() {
    val arr = mutableListOf(5, 2, 3, 0, 56, 7, 5, 7, 9)
    println(SelectionSort.sort(arr))
}

// Tijdscomplexiteit ~n^2/2
// Compares, n-1 + n-2 ... + 2 + 1 = n*(n-1)/2
// Swaps, 1 + 1 + 1 ... + 1 = n-1