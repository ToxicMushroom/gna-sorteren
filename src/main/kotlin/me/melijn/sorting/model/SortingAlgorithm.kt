package me.melijn.sorting.model

abstract class SortingAlgorithm(val inplace: Boolean) {


    var exchanges = 0
    var compares = 0

    fun <T : Comparable<T>> sort(list: MutableList<T>): MutableList<T> {
        exchanges = 0
        compares = 0
        return internalSort(list)
    }

    protected abstract fun <T : Comparable<T>> internalSort(toSort: MutableList<T>): MutableList<T>

    fun <T : Comparable<T>> isLarger(toSort: List<T>, maybeLargerAt: Int, targetAt: Int): Boolean {
        compares++
        val maybeLargerItem = toSort[maybeLargerAt]
        val target = toSort[targetAt]
        return maybeLargerItem > target
    }
    fun <E> MutableList<E>.swap(index1: Int, index2: Int) {
        exchanges++
        val oldI = this[index1]
        val newI = this[index2]
        this[index1] = newI
        this[index2] = oldI
    }
}