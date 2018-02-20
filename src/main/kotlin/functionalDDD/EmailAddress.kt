package functionalDDD

// tag::email_addresses[]
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
// end::email_factory[]

val t = createEmail("ich@inter.net")
val value = when(t) {
    is Some -> println(t.value)
    is None -> println("")
}


// tag::email_confirmation[]
fun confirmEmail(addressUnconfirmed: UnconfirmedEmailAddressAddress, token: EmailVerificationToken): Option<ConfirmedEmailAddressAddress> {
    return if(token.token == "#validToken") {
        Some(ConfirmedEmailAddressAddress(addressUnconfirmed.address))
    } else {
        None()
    }
}
// end::email_confirmation[]
