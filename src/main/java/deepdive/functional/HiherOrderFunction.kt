package deepdive.functional

fun greaterThan(x: Int): (Int) -> Boolean = {
    y -> y > x
}

fun <T> mapX(x: Int, f: (Int) -> T) = {
    f(x)
}