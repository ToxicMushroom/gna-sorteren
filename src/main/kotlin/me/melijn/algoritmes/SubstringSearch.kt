package me.melijn.algoritmes

class SubstringSearch {

    /**
     * Works by brute-forcing character matches
     * Time complexity: ~N*M
     * @param string string's length is the N
     * @param containsString string's length is the M
     *
     * @returns the start index of a search hit
     */
    fun contains(string: String, containsString: String): Int {
        for (i in string.indices) {
            for (j in containsString.indices) {
                if (string[i] == containsString[j]) {
                    if (j == containsString.length - 1) {
                        return i - containsString.length
                    }
                }
            }
        }

        return -1
    }

    // TODO: Boyer Moore
    // TODO: Rabin Karp (las vegas variant = if hash matches, check the match, monte carlo variant = if hash is equal,
    //  assume match [gebruik hier een grootte modulo voor veel hash waardes en een kleine kans op false-gevallen])

//    fun containsRabinKarp(string: String, pattern: String): Int {
//        if (string.length < pattern.length) return -1
//        var hash = pattern.hashCode()
//        var part = string.substring(0, pattern.length)
//        var i = 0
//        while (hash != part.hashCode() && pattern != part) {
//            part = part.drop(1) + string[i + pattern.length]
//        }
//    }
//
//    fun hashChar(char: Char) {
//        char.code & 997
//    }

    fun containsKMP(string: String, pattern: String): Int {
        val dfa = buildDFA(pattern)
        val m = pattern.length
        var j = 0

        for (i in string.indices) {
            j = dfa[string[i]]?.get(j) ?: 0
            if (j == m) return i - m + 1
        }
        return -1
    }

    /**
     * Complexity: M*R
     */
    private fun buildDFA(pattern: String): Map<Char, MutableMap<Int, Int>> {
        val dfa = mutableMapOf<Char, MutableMap<Int, Int>>()
        val chars = pattern.toHashSet()
        val patternChar1 = pattern[0]
        val mapc1 = dfa.getOrDefault(patternChar1, mutableMapOf())
        mapc1[0] = 1
        dfa[patternChar1] = mapc1
        var x = 0 // restart state
        for (j in 1 until pattern.length) {
            for (c in chars) {
                val resetedMap = dfa.getOrDefault(c, mutableMapOf())
                val afterResetResult = resetedMap[x] ?: 0
                if (afterResetResult != 0) {
                    resetedMap[j] = afterResetResult
                    dfa[c] = resetedMap
                }
            }
            val jthChar = pattern[j]
            val jMap = dfa.getOrDefault(jthChar, mutableMapOf())
            jMap[j] = j + 1
            x = jMap[x] ?: 0
            dfa[jthChar] = jMap
        }
        return dfa
    }
}

fun main(args: Array<String>) {
    val string = "pifhjerwiuoyhfgoieruhfgiourheiwugyh rhihgioruehygiehffgi uhenifjhnasifhlhfdashf klj dhfoiewjqwrhdskjhfkhja h dadaf iqweuioure hfeiuwfuqywiuyrieuwyvnqoiu fiouwehfq"
    val pattern = "dada"
    val res = SubstringSearch().containsKMP(string, pattern)
    println(res)
    println(string.substring(res, res + pattern.length))
}