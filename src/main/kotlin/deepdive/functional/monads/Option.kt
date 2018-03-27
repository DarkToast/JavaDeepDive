package deepdive.functional.monads

sealed class Option<T> {
    abstract fun <U> map( f: (T) -> U ): Option<U>

    abstract fun <U> flatMap( f: (T) -> Option<U> ): Option<U>

    abstract fun get(): T?

    abstract fun isPresent(): Boolean

    abstract fun getOrElse(fallbackValue: T): T

    companion object {
        fun <T> of(value: T): Option<T> {
            return if(value == null) None() else Some(value)
        }
    }
}


class Some<T> (private val value: T) : Option<T>() {
    override fun <U> map( f: (T) -> U ): Option<U> = Option.of( f(value) )

    override fun <U> flatMap( f: (T) -> Option<U> ): Option<U> = f(value)

    override fun get(): T? = value

    override fun isPresent(): Boolean = true

    override fun getOrElse(fallbackValue: T): T = value
}


class None<T>: Option<T>() {
    override fun <U> map( f: (T) -> U ): Option<U> = this as None<U>

    override fun <U> flatMap( f: (T) -> Option<U> ): Option<U> = this as None<U>

    override fun get(): T? = null

    override fun isPresent(): Boolean = true

    override fun getOrElse(fallbackValue: T): T = fallbackValue
}