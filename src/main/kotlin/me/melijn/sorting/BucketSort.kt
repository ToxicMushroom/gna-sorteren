package me.melijn.sorting

object BucketSort {

    private val buckets = mutableMapOf<Char, MutableList<String>>()

    fun sort(toSort: MutableList<String>): MutableList<String> {
        if (toSort.size <= 1) return toSort
        for (c in 'a'..'z') buckets[c] = mutableListOf()
        for (element in toSort) buckets[element[0].lowercaseChar()]?.add(element)
        val sorted = mutableListOf<String>()
        for ((_, entries) in buckets) {
            sorted.addAll(QuickSort.sort(entries))
        }

        return sorted
    }
}

fun main() {
    println(
        BucketSort.sort(Names)
    )
}
// W: word length
// R: different characters
// Each word needs to be the same length or missing characters need to be replaced with some other letter
// ~7WN + 3WR
// ~N+R extra space