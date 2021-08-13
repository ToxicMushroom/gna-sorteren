import me.melijn.algoritmes.model.MinHeap
import me.melijn.sorting.utils.getRandomIntList
import org.junit.jupiter.api.Test

class HeapTests {

    @Test
    fun testHeapFunctionality() {
        val minHeap = MinHeap<Int>()
        for (i in getRandomIntList(10, 0..100))
            minHeap.add(i)
        var last = Int.MIN_VALUE
        val popOrder = mutableListOf<Int>()
        while (minHeap.heapSize != 0) {
            val popped = minHeap.poll()
            assert(popped >= last)
            last = popped
            popOrder.add(popped)
        }
        println("MinHeap: " + popOrder.joinToString())
    }

    @Test
    fun testChildIndexesUtil() {
        val s = MinHeap.getChildIndexes(0)
        assert(s == Pair(1, 2))
        val s2 = MinHeap.getChildIndexes(1)
        assert(s2 == Pair(3, 4))
        val s3 = MinHeap.getChildIndexes(2)
        assert(s3 == Pair(5, 6))
    }


    @Test
    fun testParentIndex() {
        val s = MinHeap.getParentIndex(1)
        assert(s == 0)
        val s2 = MinHeap.getParentIndex(2)
        assert(s2 == 0)

        val s3 = MinHeap.getParentIndex(3)
        assert(s3 == 1)
        val s4 = MinHeap.getParentIndex(4)
        assert(s4 == 1)

        val s5 = MinHeap.getParentIndex(5)
        assert(s5 == 2)
        val s6 = MinHeap.getParentIndex(6)
        assert(s6 == 2)
    }
}