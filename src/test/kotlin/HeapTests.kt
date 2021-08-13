import me.melijn.algoritmes.model.Heap
import me.melijn.sorting.utils.getRandomIntList
import org.junit.jupiter.api.Test

class HeapTests {

    @Test
    fun testHeapFunctionality() {
        val maxHeap = Heap<Int>(Comparator.comparingInt { it })
        for (i in getRandomIntList(10, 0..100))
            maxHeap.add(i)
        var last = Int.MAX_VALUE
        var popOrder = mutableListOf<Int>()
        while (maxHeap.heapSize != 0) {
            val popped = maxHeap.pop()
            assert(popped <= last)
            last = popped
            popOrder.add(popped)
        }
        println("MaxHeap: " + popOrder.joinToString())

        val minHeap = Heap<Int>(Comparator.comparingInt<Int> { it }.reversed())
        for (i in getRandomIntList(10, 0..100))
            minHeap.add(i)
        last = Int.MIN_VALUE
        popOrder = mutableListOf()
        while (minHeap.heapSize != 0) {
            val popped = minHeap.pop()
            assert(popped >= last)
            last = popped
            popOrder.add(popped)
        }
        println("MinHeap: " + popOrder.joinToString())
    }

    @Test
    fun testChildIndexesUtil() {
        val s = Heap.getChildIndexes(0)
        assert(s == Pair(1, 2))
        val s2 = Heap.getChildIndexes(1)
        assert(s2 == Pair(3, 4))
        val s3 = Heap.getChildIndexes(2)
        assert(s3 == Pair(5, 6))
    }


    @Test
    fun testParentIndex() {
        val s = Heap.getParentIndex(1)
        assert(s == 0)
        val s2 = Heap.getParentIndex(2)
        assert(s2 == 0)

        val s3 = Heap.getParentIndex(3)
        assert(s3 == 1)
        val s4 = Heap.getParentIndex(4)
        assert(s4 == 1)

        val s5 = Heap.getParentIndex(5)
        assert(s5 == 2)
        val s6 = Heap.getParentIndex(6)
        assert(s6 == 2)
    }
}