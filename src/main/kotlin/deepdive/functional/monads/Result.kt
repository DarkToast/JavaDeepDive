package deepdive.functional.monads

sealed class Result<S, F> {
    abstract fun <T> flatMap( f : (S) -> Result<T, F> ): Result<T, F>

    abstract fun <T> map( f: (S) -> T ): Result<T, F>

    abstract fun recover( recover: (F) -> Success<S, F> ): Result<S, F>

    abstract fun getOrElse( recoverValue: S ): S

    companion object {
        fun <S, F> of(value: S): Result<S, F> = Success(value)
    }
}



class Success<S, F>(val value: S) : Result<S, F>() {
    override fun <T> flatMap( f: (S) -> Result<T, F> ): Result<T, F> = f(value)

    override fun <T> map( f: (S) -> T ): Result<T, F> = Success(f(value))

    override fun recover(recover: (F) -> Success<S, F>): Result<S, F> = this

    override fun getOrElse( recoverValue: S ): S = value
}



class Failure<S, F>(val failure: F) : Result<S, F>() {
    override fun <T> flatMap(f: (S) -> Result<T, F>): Result<T, F> = Failure(failure)

    override fun <T> map(f: (S) -> T): Result<T, F> = Failure(failure)

    override fun recover(recover: (F) -> Success<S, F>): Result<S, F> = recover(failure)

    override fun getOrElse(recoverValue: S): S = recoverValue
}





