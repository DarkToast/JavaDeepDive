package functionalDDD


// tag::validation[]
class Firstname private constructor(val name: String) {
    companion object {
        fun create(name: String): Option<Firstname> {
            return if (name.length <= 50) Some(Firstname(name)) else None()
        }
    }
}

val option = Firstname.create("Max.Mustermann")
val name = when(option) {
    is Some -> option.value.name
    is None -> "Error Error"
}
// end::validation[]


// tag::explicit_error_handling[]
sealed class Result<S, F>

class Success<S, F>(val value: S) : Result<S, F>()
class Failure<S, F>(val failure: F) : Result<S, F>()


class CreationFailure(val message: String)

fun create(name: String): Result<FirstName, CreationFailure> {
    return if (name.length <= 50) {
        Success(FirstName(name))
    } else {
        Failure(CreationFailure("Name exceeded 50 characters."))
    }
}


val result = create("Max.Mustermann.Hupplifluppi")

val x: String = when (result) {
    is Success -> result.value.name
    is Failure -> result.failure.message
}
// end::explicit_error_handling[]
