package functionalDDD

// tag::simple_types[]
data class Name(val value: String)

// Factory function
fun name(value: String): Name {
    require(value.length <= 50) { "Name must be less or equals 50 characters" }
    return Name(value)
}
// end::simple_types[]


// tag::kotlin_init_validation[]
data class Lastname(val value: String) {
    init { require(value.length <= 50) { "Last name must be less or equals 50 characters" } }
}
// end::kotlin_init_validation[]


// tag::and_types[]
data class Person(val name: Name, val lastName: LastName)

val p = Person(Name("Max"), LastName("Mustermann"))
// end::and_types[]


// tag::or_types[]
sealed class Fruit
class Apple: Fruit()
class Orange: Fruit()

val f: Fruit = Orange()

// Type check:
val s = when(f) {
    is Apple -> "apple"
    is Orange -> "orange"
}
// end::or_types[]