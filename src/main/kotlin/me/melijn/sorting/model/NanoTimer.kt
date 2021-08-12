package me.melijn.sorting.model

class NanoTimer {

    var startTime: Long = System.nanoTime()
    var stopTime: Long? = null

    fun start() {
        startTime = System.nanoTime()
    }

    /**
     * @returns the duration between stopTime and startTime
     */
    fun stop(): Long {
        val current = System.nanoTime()
        stopTime = current
        return current - startTime
    }
}