package me.melijn.algoritmes.model

/**
 * Default comparator is a MinHeap
 */
open class MinHeap<T : Comparable<T>> {

    private val heapArray = ArrayList<T>()
    val heapSize: Int
        get() = heapArray.size

    fun add(el: T) {
        heapArray.add(el)
        swimUp(heapSize - 1, heapArray[heapSize - 1])
    }

    fun lookMax(): T? = heapArray.getOrNull(0)

    fun poll(): T {
        if (heapArray.size == 0)
            throw IllegalStateException("Trying to pop with no elements on the heap, please check the heapSize")

        val popped = heapArray[0]

        val lastValue = heapArray[heapSize - 1]
        heapArray[0] = lastValue // Set the top element to the last heap element
        heapArray.removeAt(heapSize - 1) // remove the last heap element so it isn't duplicated
        sinkDown(0, lastValue) // then sink it to its correct position.
        return popped
    }

    /**
     * Sinks down the top element in the heapArray
     */
    private fun sinkDown(index: Int, value: T) {
        val (child1Index, child2Index) = getChildIndexes(index)

        val child1 = heapArray.getOrNull(child1Index)
        val child2 = heapArray.getOrNull(child2Index)
        val (smallestChildIndex, smallestChild) =
            if (child1 != null && child2 != null) {
                // if child1 is smaller than child2 we select child1
                if (child1 < child2) child1Index to child1
                else child2Index to child2
            } else if (child1 == null) {
                if (child2 == null) return
                child2Index to child2
            } else child1Index to child1

        if (value > smallestChild) { // if the value is bigger than the smallest child, we swap em
            heapArray[index] = smallestChild
            heapArray[smallestChildIndex] = value
            sinkDown(smallestChildIndex, value) // recursively check whether the value needs to be sunk further
        }
    }

    /**
     * Swims up the last element in the heapArray
     */
    private fun swimUp(index: Int, value: T) {
        if (index == 0) return
        val parentIndex = getParentIndex(index)

        val parent = heapArray[parentIndex]
        if (parent > value) { // if the parent is bigger than the swimming child, we swap em
            heapArray[parentIndex] = value
            heapArray[index] = parent
            swimUp(parentIndex, value) // recursively check whether the value needs to be swum up further
        }
    }

    companion object {
        inline fun getChildIndexes(index: Int) = (((index + 1) * 2) - 1 to ((index + 1) * 2))
        inline fun getParentIndex(index: Int): Int = ((index - 1) / 2)
    }
}