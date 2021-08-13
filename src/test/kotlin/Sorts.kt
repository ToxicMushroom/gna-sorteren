import me.melijn.sorting.comparing.*
import me.melijn.sorting.model.Timer
import me.melijn.sorting.utils.getRandomIntList
import me.melijn.sorting.utils.isSorted
import org.junit.jupiter.api.Test

class Sorts {

    @Test
    fun testComparingSorts() {
        val algorithms = listOf(
            SelectionSort(),
            InsertionSort(),
            QuickSort(),
            MergeSort(),
            HeapSort()
        )

        for (algorithm in algorithms) {
            println("--- Warmup ${algorithm.javaClass.name}")
            val toSort = getRandomIntList(20000, 0..10000)
            algorithm.sort(toSort)
        }

        for (algorithm in algorithms) {
            println("--- Selected ${algorithm.javaClass.name}")
            val timer = Timer()
            val toSort = getRandomIntList(30000, 0..10000)
            println("To sort: ${toSort.size} elements")
            val sorted = if (algorithm.inplace) {
                algorithm.sort(toSort)
                toSort
            } else algorithm.sort(toSort)
            println("Time: ${timer.stop()}ms")
            println("Exchanges: ${algorithm.exchanges}")
            println("Compares: ${algorithm.compares}")
            if (!isSorted(sorted)) throw IllegalStateException("${algorithm.javaClass.name} doesn't sort correctly.")
        }
    }
}