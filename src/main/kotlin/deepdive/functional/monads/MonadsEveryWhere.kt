package deepdive.functional.monads

import java.lang.Thread.sleep
import java.util.concurrent.CompletableFuture
import java.util.concurrent.TimeUnit

// Kotlin List:
val list = listOf(1, 2, 3, 4)
    .map { i -> i + 1 }
    .flatMap { i -> listOf(i - 1, i, i + 1) }


// Java8 Futures:
val futureHello: String = CompletableFuture.supplyAsync {
    sleep(500)
    "Hello"
}
.thenComposeAsync { hello -> CompletableFuture.supplyAsync {            // Like flatMap  `Type -> Future<OtherType>`
    sleep(500)
    hello + " world"
}}
.thenApply { helloWorld ->                                              // Like map `Type -> OtherType`
    helloWorld + "!"
}
.get(2, TimeUnit.SECONDS)



fun main(args: Array<String>) {
    println(list)
    println(futureHello)
}
