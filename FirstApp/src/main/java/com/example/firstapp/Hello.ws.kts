println("Hello World !")
2 * 3

val i: Int = 3
val name = "Jean à $i ans"
println(name)

val longText = """
    Very long text
    with saut de ligne
    need triple quotes
""".trimIndent()
println("Ce long text a ${longText.length} caractères")

val nb1 = 10
val nb2 = 20
println("La somme de $nb1 et $nb2 est ${nb1 + nb2}")

var width: Int = 12
if (width == 12) {
    println("ça vaut 12")
} else {
    println("ça ne vaut pas 12")
}
if (width in 1..10) {
    println("est dans l'interval")
} else {
    println("n'est pas dans l'interval")
}

when (width) {
    0 -> println("vaut 0")
    in 1..10 -> println("vaut moins de 10")
    else -> println("vaut plus de 10")
}
while (width < 50) {
    println(width)
    width++
}

val pets = arrayOf("Dog", "Cat")
for ((index, pet) in pets.withIndex()) {
    println("L'animal $index est un $pet")
}
for (i in 0..(pets.size - 1)) {
    println("L'animal $i est un ${pets[i]}")
}

for (i in 5 downTo 1) {
    println(i)
}

for (i in 'd'..'g') {
    println(i)
}

val planets = listOf("Mercure", "Venus", "Uranus", "Saturne", "Terre")
println(planets)
println(planets.size)
println(planets.first())
println(planets.last())

planets.first {
    it.startsWith("M")
}

fun sayHello() {
    println("Hello World")
}

fun sayHelloTo(name: String) {
    print("Hello $name")
}

sayHello()
sayHelloTo("Theo")
sayHelloTo(name = "Theo")

fun sum(nb1: Int, nb2: Int) {
    sayHelloTo("You")
    val s = nb1.plus(nb2)
    println("La somme est $s")
    sayHello()
}

sum(2, 3)

fun sayHello1(): String {
    return "Hello World"
}

val result = sayHello1()
println(result)

fun sum1(nb1: Int, nb2: Int): Int {
    return nb1.plus(nb2)
}

val sum = sum1(3, 9)
println(sum)

fun sum2(nb1: Int, nb2: Int, operation: String): Int {
    return when (operation) {
        "add" -> nb1 + nb2
        "times" -> nb1 * nb2
        "diff" -> nb1 - nb2
        "div" -> nb1 / nb2
        else -> nb1 + nb2
    }
}

println(sum2(14, 13, "times"))

val nbs = mutableListOf<Int>()

repeat(50) {
    nbs.add(it)
}
println(nbs)

val res = nbs.filter {
    it % 2 == 0
}

println(res)
