package deepdive.functional

import java.util.stream.Stream

fun greaterThan(x: Int): (Int) -> Boolean = {
    y -> y > x
}

fun <T> mapX(x: Int, f: (Int) -> T) = {
    f(x)
}

val y = run {
    val f = greaterThan(10)
    f(6)

    mapX(10, { x -> x * 10 })

    Stream.of(1, 2, 3).map { i -> i.toString() }
}