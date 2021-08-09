import me.melijn.sorting.*
import me.melijn.sorting.model.Timer
import org.junit.jupiter.api.Test

class Sorts {

    @Test
    fun testSort() {
        val algorithms = listOf(QuickSort(), SelectionSort(), InsertionSort(), MergeSort())

        for (algorithm in algorithms) {
            println("--- Selected ${algorithm.javaClass.name}")
            val timer = Timer()
            val toSort = getRandomIntList(20000, 0..10000)
            println("To sort: ${toSort.size} elements")
            val sorted = if (algorithm.inplace) {
                algorithm.sort(toSort)
                toSort
            } else algorithm.sort(toSort)
            if (!isSorted(sorted)) throw IllegalStateException("${algorithm.javaClass.name} doesn't sort correctly.")
            println("Time: ${timer.stop()}ms")
            println("Exchanges: ${algorithm.exchanges}")
            println("Compares: ${algorithm.compares}")
        }
    }
}