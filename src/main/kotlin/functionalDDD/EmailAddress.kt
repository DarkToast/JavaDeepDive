package functionalDDD

// tag::email_addresses[]
data class EmailVerificationToken(val token: String)

sealed class EmailAddress(address: String)
data class ConfirmedEmailAddressAddress(val address: String): EmailAddress(address)
data class UnconfirmedEmailAddressAddress(val address: String): EmailAddress(address)
// end::email_addresses[]


// tag::email_factory[]
fun createEmail(address: String): Option<EmailAddress> {
    val emailRegex = """^[^@\s]+@[^@\s]+\.[^@\s]+${'$'}""".toRegex()

    return if(emailRegex.matches(address)) {
        Some(UnconfirmedEmailAddressAddress(address))
    } else {
        None()
    }
}

val t = createEmail("ich@inter.net")
val value = when(t) {
    is Some -> println(t.value)
    is None -> println("")
}
// end::email_factory[]

// tag::email_confirmation[]
fun confirmEmail(
        addressUnconfirmed: UnconfirmedEmailAddressAddress,
        token: EmailVerificationToken
): Option<ConfirmedEmailAddressAddress> {

    return if(token.token == "#validToken") {
        Some(ConfirmedEmailAddressAddress(addressUnconfirmed.address))
    } else {
        None()
    }
}
// end::email_confirmation[]
