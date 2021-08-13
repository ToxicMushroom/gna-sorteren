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
                if (string[i + j] == containsString[j]) {
                    if (j == containsString.length - 1) {
                        return i
                    }
                } else break
            }
        }

        return -1
    }

    /**
     * Works by brute-forcing character matches, but skip over letters that don't occur in our patterns, or max skip letters if they occur
     * Worst case time complexity: ~N*M
     * @param string string's length is the N
     * @param pattern string's length is the M
     *
     * @returns the start index of a search hit
     */
    fun containsBoyerMoore(string: String, pattern: String): Int {
        val lastMap = mutableMapOf<Char, Int>()
        for (c in pattern.toSet()) lastMap[c] = pattern.lastIndexOf(c)
        var i = pattern.length - 1
        while (i < string.length) {
            for (j in pattern.indices) {
                val backI = i - j
                val backJ = pattern.length - 1 - j

                val onChar = string[backI]
                val pChar = pattern[backJ]
                if (onChar != pChar) {
                    val lastAt = lastMap.getOrDefault(onChar, -1)
                    val needMove = if (lastAt == -1) pattern.length - j
                    else pattern.length - lastAt - 1
                    i += needMove
                    break
                } else if (backJ == 0) return backI
            }
        }

        return -1
    }


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
    val string =
        "pifhjerwiuoyhfgoieruhfgiourheiwugyh rhihgioruehygiehffgi uhenifjhnasifhlhfdashf klj dhfoiewjqwrhdskjhfkhja h daddadaf iqweuioure hfeiuwfuqywiuyrieuwyvnqoiu fiouwehfq"
    val pattern = "dada"
    val bruteRes = SubstringSearch().contains(string, pattern)
    println(bruteRes)
    val res = SubstringSearch().containsKMP(string, pattern)
    println(res)
    val res2 = SubstringSearch().containsBoyerMoore(string, pattern)
    println(res2)
    val res3 = SubstringSearch().containsBoyerMoore("ihfsdaodqfhdshdadaddadadadaoahddoshaodh", "dadadada")
    println(res3)
    println(string.substring(res, res + pattern.length))
}