package deepdive.functional

fun add(x: Int, y: Int): Int {
    return x + y
}

val fAdd: (Int, Int) -> Int = { x, y ->
    x + y
}

val fAdd2 = { x: Int, y: Int ->
    x + y
}

val x = run {
    fAdd(1, 2)
    fAdd2(1, 2)
}