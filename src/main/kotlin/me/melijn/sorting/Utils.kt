package me.melijn.sorting

import kotlin.random.Random

object Utils {

    fun <T : Comparable<T>> isLarger(toSort: List<T>, maybeLargerAt: Int, targetAt: Int): Boolean {
        val maybeLargerItem = toSort[maybeLargerAt]
        val target = toSort[targetAt]
        return maybeLargerItem > target
    }
}
fun <E> MutableList<E>.swap(index1: Int, index2: Int) {
    val oldI = this[index1]
    val newI = this[index2]
    this[index1] = newI
    this[index2] = oldI
}

val Names =  mutableListOf(
    "Tiny Nielsen",
    "Sonny Valletta",
    "Nancey Batey",
    "Zula Walkup",
    "Wynell Lillibridge",
    "Mendy Reinecke",
    "Mindy Pearman",
    "Rueben Chauncey",
    "Myrl Billy",
    "Kieth Whitt",
    "Pauletta Tijerina",
    "Kathi Tygart",
    "Jeni Chamness",
    "Elisha Torbert",
    "Cristobal Strahan",
    "Serafina Merrow",
    "Shawnee Smelley",
    "Nisha Reidy",
    "See Visitacion",
    "Vanita Kight",
    "Jeanetta Krawiec",
    "Corie Hochman",
    "Jeffry Polito",
    "Shantelle Mccrady",
    "Kimbery Mccaul",
    "Nicholas Sutcliffe",
    "Dustin Boysen",
    "Mertie Hance",
    "Moon Coghlan",
    "Carmella Valenti",
    "Isiah Mcneese",
    "Hunter Macintosh",
    "Bo Culotta",
    "Mao Bowerman",
    "Emerson Mersch",
    "Deandra Letchworth",
    "Kasha Maycock",
    "Ruthe Hinchey",
    "Isaura Ceniceros",
    "Cherly Malagon",
    "Thad Hillis",
    "Vanda Dishner",
    "Glen Gil",
    "Patrick Pakele",
    "Mattie Catalano",
    "Clora Weitz",
    "Marsha Stefano",
    "Hipolito Sjogren",
    "Lucila Prudhomme",
    "Steffanie Roussel",
    "Donnette Pagel",
    "Porsha Mccorvey",
    "Madonna Ryerson",
    "Bobette Halas",
    "Mozelle Jefcoat",
    "Samantha Angert",
    "Bibi Rathbun",
    "Dale Urbina",
    "Herschel Buttrey",
    "Delena Hering",
    "Sean Isaac",
    "Ozell Rappold",
    "Olevia Kinnaman",
    "Audrie Germano",
    "Dorene Deering",
    "Dahlia Millman",
    "Yolande Winzer",
    "Odelia Siddiqi",
    "Cyndi Brickner",
    "Chelsea Gauvin",
    "Donya Fitchett",
    "Billye Sagucio",
    "Alphonse Dacruz",
    "Mimi Noack",
    "Stefany Mike",
    "Bernita Odegaard",
    "Chia Mcabee",
    "Gudrun Darst",
    "Roberto Gomez",
    "Robby Northrop",
    "Roselle Filippone",
    "Hershel Stenger",
    "Carmela Claypool",
    "Stephaine Billick",
    "Marilynn Suzuki",
    "Arlen Stroop",
    "Rene Gianni",
    "Coleen Villa",
    "Julius Yost",
    "Berry Quayle",
    "Alex Pair",
    "Paola Crowe",
    "Mackenzie Knoblock",
    "Parker Jin",
    "Shawn Nickles",
    "Marian Seago",
    "Chance Julius",
    "Amos Daquila",
    "Angel Nicosia",
    "Kennith Gasser"
)


val RandomEqualStrings = Array(200) { // 20 Strings
    Array(10) { // 10 characters long
        Random.nextInt('a'.code, 'z'.code + 1).toChar()
    }.joinToString("")
}.toMutableList()