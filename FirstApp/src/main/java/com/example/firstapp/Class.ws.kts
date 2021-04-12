package com.example.firstapp

class Etudiant(
    var name: String,
    var surname: String,
    val id: Int
)

val a = Etudiant("Maëva", "Mezzasalma", 1)
a.name = "Maë"

println(a.name)

class Address(var name: String)

class Person {
    var first: String
    var last: String
        get() = "Le nom est $field"
        set(value) {
            if (value == "André") {
                field = "Jean Pierre"
            } else {
                field = value
            }
        }
    var age: Int
    var address: Address
    var fullName: String
        get() = "$first $last"

    constructor(fullName: String, address: Address) {
        val params = fullName.split(" ")
        first = params.getOrNull(0) ?: "N'importe quoi"
        last = params.getOrElse(1) { "C'est quoi ça ?" }
        age = 34
        this.address = address
        this.fullName = fullName
    }

    fun sayMyName() {
        println(fullName)
    }
}

val p = Person(
    "André Pierre",
    address = Address("Annecy")
)

p.address.name
p.first
p.sayMyName()
