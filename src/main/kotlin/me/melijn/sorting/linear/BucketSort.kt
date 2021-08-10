package me.melijn.sorting.linear

import me.melijn.sorting.comparing.QuickSort
import me.melijn.sorting.utils.Names

object BucketSort {

    private val buckets = mutableMapOf<Char, MutableList<String>>()

    fun sort(toSort: MutableList<String>): MutableList<String> {
        if (toSort.size <= 1) return toSort
        for (c in 'a'..'z') buckets[c] = mutableListOf()
        for (element in toSort) buckets[element[0].lowercaseChar()]?.add(element)
        val sorted = mutableListOf<String>()
        for ((_, entries) in buckets) {
            sorted.addAll(QuickSort().sort(entries))
        }

        return sorted
    }
}

fun main() {
    println(
        BucketSort.sort(Names)
    )
}

/** WORST CASE **/
// All elements in 1 bucket
// No help at all, will be as fast as the sorting algorithm used

/** AVERAGE CASE/BEST CASE  **/
// More or less equal-sized buckets
// Assume ~ c*n*log^2(n) sorting algorithm for each bucket
// -> k avg filled buckets then give ~ k * (c*n/k*log^2(n/k)) = ~c*n*log^2(n/k)

// k (bucket amount) should be proportional to n (element count)
// then log^2(n/k) is constant -> c'
// thus ~c*n*log^2(n/k) = ~c' * n

// if k (bucket amount) is not proportional but constant, we will keep  ~c*n*log^2(n)

/** NOTES **/
// For very small filled buckets, n/k < 5 then insertion sort is a viable alternative
// For larger sets, quicksort or mergesort is optimal