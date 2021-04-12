class Student {
    var name: String = ""
    var code: Int = 0
    var sexe: String = ""
    var address: String = ""
    var age: Int = 10

    constructor(name: String, code: Int, sexe: String, address: String, age: Int) {
        this.name = name
        this.code = code
        this.sexe = sexe
        this.address = address
        this.age = age
    }
}

var listOfStudents = mutableListOf<Student>()

listOfStudents.add(Student("Maëva", 1, "F", "Grenoble", 22))
listOfStudents.add(Student("Théo", 2, "M", "Strasbourg", 23))
listOfStudents.add(Student("Anthony", 5, "M", "Valence", 21))
listOfStudents.add(Student("Johann", 6, "M", "Thoiry", 21))
listOfStudents.add(Student("Léo", 7, "M", "Lyon", 21))
listOfStudents.add(Student("Vincent", 8, "M", "Grenoble", 21))
listOfStudents.add(Student("Elise", 4, "F", "Vieugy", 21))
listOfStudents.add(Student("Luc", 9, "M", "Seynod", 21))
listOfStudents.add(Student("Léo", 10, "M", "Annecy", 26))
listOfStudents.add(Student("Lilian", 11, "M", "Annecy", 21))
listOfStudents.add(Student("Maëva", 3, "F", "Mottier", 21))

fun testOnList(array: List<Student>, filterBy: String) {
    when (filterBy) {
        "age" -> for (i in 1..array.size) {
            println(array[i - 1].age)
        }
        "name" -> for (i in 1..array.size) {
            println(array[i - 1].name)
        }
        "sexe" -> for (i in 1..array.size) {
            println(array[i - 1].sexe)
        }
        "code" -> for (i in 1..array.size) {
            println(array[i - 1].code)
        }
        "address" -> for (i in 1..array.size) {
            println(array[i - 1].address)
        }
    }
}

listOfStudents.sortBy { it.age } // ordre croissant
testOnList(listOfStudents, "age")

listOfStudents.sortByDescending { it.age } // ordre decroissant
testOnList(listOfStudents, "age")

listOfStudents.groupBy { it.sexe } // grouper par sexe // je comprends pas trop comment c'est sensé fonctionner
testOnList(listOfStudents, "sexe")

var women = listOf<Student>()
women = listOfStudents.filter { it.sexe == "F" }
testOnList(women, "sexe")

var men = listOf<Student>()
men = listOfStudents.filter { it.sexe == "M" }
testOnList(men, "sexe")

//  Récupérer le premier étudiant masculin et changer son prénom
var tmp = 0
while (listOfStudents[tmp].sexe == "F") {
    tmp++
}
println(listOfStudents[tmp].sexe)
println(tmp)
listOfStudents[tmp].name = "Quentin"
println(listOfStudents[tmp].name)

println(men[0].name) // le nom a été changé dans les deux listes, les sous-listes pointent vers la principale

//  Créer une liste avec uniquement les noms des étudiants
var nameOfStudents = mutableListOf<String>()
for (i in 1..listOfStudents.size) {
    nameOfStudents.add(listOfStudents[i - 1].name)
}
for (i in 1..nameOfStudents.size) {
    println(nameOfStudents[i - 1])
}

//  Supprimer les étudiants d'un sexe particulier
listOfStudents.filter { it.sexe == "F" }.forEach { listOfStudents.remove(it) }
testOnList(listOfStudents, "sexe")

//  Inverser la liste
testOnList(listOfStudents, "name")
listOfStudents.reverse()
testOnList(listOfStudents, "name")

//  Supprimer les étudiants restant / Vider la liste
println(listOfStudents)
listOfStudents.clear()
println(listOfStudents)
