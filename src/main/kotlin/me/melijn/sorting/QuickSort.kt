package me.melijn.sorting

import me.melijn.sorting.model.SortingAlgorithm

class QuickSort : SortingAlgorithm(true) {

    override fun <T : Comparable<T>> internalSort(toSort: MutableList<T>): MutableList<T> {
        sort(toSort, 0, toSort.size - 1)
        return toSort
    }

    private fun <T : Comparable<T>> sort(list: MutableList<T>, low: Int, high: Int) {
        if (high <= low) return
        val partitionIndex = partitionHoare(list, low, high)
        sort(list, low, partitionIndex - 1)
        sort(list, partitionIndex + 1, high)
    }

    // Hoare
    // [                        ]
    //  ^                      ^
    // (low,pivot)             high

    // [ | <=pivot |     | >=pivot ]
    //  ^           ^   ^
    // pivot        i   j
    private fun <T : Comparable<T>> partitionHoare(list: MutableList<T>, low: Int, high: Int): Int {
        var (i, j) = low to high + 1
        while (true) {
            while (isLarger(list, low, ++i)) if (i == high) break
            while (isLarger(list, --j, low)) if (j == low) break
            if (i >= j) break
            list.swap(i, j)
        }
        list.swap(low, j) // swap pivot
        return j
    }

    // Lomuto
    //  [                        ]    <- represents a list
    // ^ ^                      ^
    // i (j,low)                (high, pivot)

    // [ <pivot | >=pivot |j    | ]
    //  ^      ^           ^     ^-- pivot,high
    //  (low)  i           progress
    private fun <T : Comparable<T>> partitionLomuto(list: MutableList<T>, low: Int, high: Int): Int {
        var i = low-1 // index tracker for last element that is smaller than pivot
        val pivot = list[high] // pivot is most right el
        for (j in low until high) { // j in [low; high-i]
            if (isSmallerThanEl(list, j, pivot)) list.swap(++i, j)
            // Else can stay in place and j will move up 1 pos
        }
        val finalPivotIndex = i+1
        list.swap(finalPivotIndex, high) // move pivot in between smaller and bigger/equal part
        return finalPivotIndex
    }
    // [ <pivot | >=pivot | ]
    //  ^      ^           ^-- pivot,high
    //  (low)  i
    // After the for loop, pivot gets swapped with el[i+1] -> [ <pivot | | >=pivot ]
    //                                                                  ^-- pivot
}


fun main() {
    val arr = mutableListOf(5, 2, 3, 0, 56, 7, 5, 7, 9)
    println(QuickSort().sort(arr))
}
/** **/
// different parition implementations:
// 1. create 2 arrays left[] and right[], for el in elements: if el < pivot { put into left[] } else { put into right [] }, concat left[] + pivot + right[]
// 2. https://en.wikipedia.org/wiki/Quicksort#Hoare_partition_scheme (chosen for the code above)
// 3. https://en.wikipedia.org/wiki/Quicksort#Lomuto_partition_scheme

/** BEST CASE **/
// - partition takes n compares
// - split is always balanced
// T(1) = 0
// T(n) = 2T(n/2) + partitioning(n)
//      = 2T(n/2) + n
//      = 4T(n/4) + n + n
// ~ n * log^2(n)

/** WORST CASE **/
// Partition element causes 0 Elements in one half and n-1 in the other half for each partition
// T(n) = T(n-1) + partitioning(n)
//      = T(n-2) + n + n
//      = n * (n) = n^2
// ~ n^2

/** WORST CASE CHANCE **/
// we don't need to partition 1 sized arrays so those aren't counted
// 2/n * 2/n-1 * 2/n-2 ... 2/2 = 2^(n-1)/n!
// for 8: 2/8 * 2/7 * 2/6 * 2/5 * 2/4 * 2/3 * 2/2 = 2^(7)/8! = 0.003

/** AVERAGE CASE **/
// See explanation in book/les 4
// ~ 2n*log^e(2)*log^2(n) = ~ 1,39 * n * log^2(n)
// notation I used is log^base(value)