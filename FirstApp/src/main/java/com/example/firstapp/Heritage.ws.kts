//  Héritage
open class Person(var name: String, val id: Int)
open class Student(name: String, id: Int, val card: String) : Person(name, id)
class Delegue(name: String, id: Int, card: String, salaire: Double) : Student(name, id, card)

val person = Person(name = "Elise", id = 14)
val student = Student(name = "Théo", id = 12, card = "141345")
val persons: Person = Student(name = "Théo", id = 12, card = "141345")
// val student1: Student = Person(name = "Elise", id = 14)   // not working

val list = listOf<Student>(
    Student(name = "Elise", id = 14, card = "141396"),
    Student(name = "Théo", id = 12, card = "141345"),
    Delegue(name = "Léo", id = 18, card = "141416", salaire = 14000.00)
)

list.forEach {
    if (it is Delegue) {
        println("C'est un délégué")
    }
    if (it is Student) {
        println("C'est un étudiant")
    }
    if (it is Person) {
        println("C'est une personne")
    }
}

//  Interface
interface Shape {
    fun computeSurface(): Double
    fun computePerimeter(): Double
}

//  On étend une class
//  On implémente une interface

class Circle(val rayon: Double) : Shape {
    override fun computeSurface(): Double {
        return 2 * 3.14 * rayon * rayon
    }

    override fun computePerimeter(): Double {
        return 2 * 3.14 * rayon
    }
}

open class Rectangle(private val long: Double, private val larg: Double) : Shape {
    override fun computeSurface(): Double {
        return long * larg
    }

    override fun computePerimeter(): Double {
        return 2 * (long + larg)
    }
}

class Square(cote: Double) : Rectangle(cote, cote)

val shapes: List<Shape> = listOf(
    Circle(23.0),
    Rectangle(14.3, 2.0),
    Square(12.3),
    Rectangle(14.3, 2.0),
    Rectangle(14.3, 2.0),
    Square(12.3)
)

shapes.forEach {
    println(it)
    println(it.computeSurface())
    println(it.computePerimeter())
}

val squares = shapes.filterIsInstance<Square>()
