import me.melijn.sorting.comparing.*
import me.melijn.sorting.model.NanoTimer
import me.melijn.sorting.model.Timer
import me.melijn.sorting.utils.getRandomIntList
import me.melijn.sorting.utils.isSorted
import org.junit.jupiter.api.Test

class Sorts {

    @Test
    fun testComaringSorts() {
        val algorithms = listOf(SelectionSort(), InsertionSort(), QuickSort(), MergeSort(), HeapSort())

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

    @Test
    fun sex() {
        repeat(2000) { //warmup
            hot2()
        }
        val loops = 10000
        val timer = NanoTimer()
        repeat(loops) { //warmup
            hot2()
        }
        val duration = timer.stop()
        println(duration/loops)
    }

    private fun hot() {
        val id = 222046562543468545
        val content = "<@222046562543468545>"
        content.equals("<@$id>")
    }
    private fun hot2() {
        val id = 222046562543468545
        val content = "<@!222046562543468545>"
        content.equals("<@$id>") || content.equals("<@!$id>")
    }
}