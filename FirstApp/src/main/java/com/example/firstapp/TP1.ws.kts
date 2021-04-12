// Print number even or odd
fun even(number: Int) {
    for (i in 0..number) {
        if (i % 2 == 0) {
            println(i)
        }
    }
}
even(13)
fun odd(number: Int) {
    for (i in 0..number) {
        if (i % 2 == 1) {
            println(i)
        }
    }
}
odd(13)

// fun evenOdd(number:Int, operator:String) {
//    val n:Int
//    when(operator) {
//        "even" -> n = 0
//        "odd" -> n = 1
//        else -> n = 0
//    }
//    for (i in 0..number) {
//        if (i % 2 == n) {
//            println(i)
//        }
//    }
// }
// evenOdd(9,"odd")

// Fibonacci suit
fun fibonacci() {
    var n2 = 0
    var n1 = 1
    var n: Int
    var i = 0

    while (i <= 15) {
        when (i) {
            0 -> n = 0
            1 -> n = 1
            else -> {
                n = n1 + n2
                n2 = n1
                n1 = n
            }
        }
        println(n)
        i++
    }
}
fibonacci()

// Factorelle
fun factorielle(x: Int = 10): Int {
    var result = 1
    for (i in 1..x) {
        result *= i
//        println(result)
    }
    return result
}
println(factorielle(x = 9))

// First Numbers
fun numbers(x: Int) {

    var index = 2 // 0 et 1 ne comptent pas
    var nbrPre = 0 // compteur de nbr premiers

    while (nbrPre < x) {
        var div = 0 // nbr de fois que index a pu être divisé par un chiffre positif inférieur
        for (i in 1..index) {
            if (index % i == 0) {
                div++
            }
        }
        if (div == 2) {
            println(index)
            nbrPre++
        }
        index++
    }
}

numbers(13)
