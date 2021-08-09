package me.melijn.sorting.model

class Timer {

    var startTime: Long = System.currentTimeMillis()
    var stopTime: Long? = null

    fun start() {
        startTime = System.currentTimeMillis()
    }

    /**
     * @returns the duration between stopTime and startTime
     */
    fun stop(): Long {
        val current = System.currentTimeMillis()
        stopTime = current
        return current - startTime
    }
}