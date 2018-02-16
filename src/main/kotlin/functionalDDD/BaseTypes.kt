package functionalDDD

// tag::option[]
sealed class Option<T>
class Some<T> (val value: T): Option<T>()
class None<T>: Option<T>()
// end::option[]


// tag::base_types[]
class FirstName(val name: String)

class MiddleName(val name: String)

class LastName(val name: String)

class Password(private val clear: String) {
    val hash = clear + "_hash"
}

data class DataUsageOptIn(val optIn: Boolean)
// end::base_types[]




data class EmailVerificationToken(val token: String)


