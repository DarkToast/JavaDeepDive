package functionalDDD

// tag::simple_types[]
class Name(val value: String)

class Lastname(val value: String)
// end::simple_types[]


// tag::and_types[]

// AND types : A combination of several types.
class Person(val name: Name, val lastName: LastName)

val p = Person(Name("Max"), LastName("Mustermann"))
// end::and_types[]



// tag::or_types[]

// OR types : Or "either" types. A fruit can either be an apple, an orange or a pear. But not more.
sealed class Fruit
class Apple: Fruit()
class Orange: Fruit()
class Pear: Fruit()


val f: Fruit = Orange()

// Type check - Results in orange.
val s = when(f) {
    is Apple -> "apple"
    is Orange -> "orange"
    is Pear -> "pear"
}
// end::or_types[]