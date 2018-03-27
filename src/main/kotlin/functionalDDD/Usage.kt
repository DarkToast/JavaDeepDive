package functionalDDD

import java.util.concurrent.CompletableFuture
import java.util.concurrent.Future

val c = UnverifiedCustomer(
    FirstName("Max"),
    None(),
    LastName("Mustermann"),
    UnconfirmedEmailAddressAddress("ich@inter.net"),
    Password("s3cr3t"),
    DataUsageOptIn(false),
    EmailVerificationToken("#token")
)


fun needVerification(c: Customer): Boolean {
    return when (c) {
        is UnverifiedCustomer -> true
        is VerifiedCustomer -> false
    }
}