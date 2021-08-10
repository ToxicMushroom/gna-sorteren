import me.melijn.sorting.comparing.InsertionSort
import me.melijn.sorting.comparing.MergeSort
import me.melijn.sorting.comparing.QuickSort
import me.melijn.sorting.comparing.SelectionSort
import me.melijn.sorting.model.Timer
import me.melijn.sorting.utils.getRandomIntList
import me.melijn.sorting.utils.isSorted
import org.junit.jupiter.api.Test

class Sorts {

    @Test
    fun testComaringSorts() {
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