package functionalDDD

// tag::naiv_customer[]
data class NaivCustomer(
    val firstName: String,          // Max 30 characters
    val middleName: String,         // Max 50 characters; Optional
    val lastName: String,           // Max 20 characters

    val email: String,              // Regex address
    val emailConfirmed: Boolean,    //Hmmm. Workflow?

    val password: String,           // clear or hashed ? Hashed: Byte[]?
    val dataUsageOptIn: Boolean
)
// end::naiv_customer[]

sealed class Customer

data class VerifiedCustomer(
    val firstName: FirstName,
    val middleName: Option<MiddleName>,
    val lastName: LastName,

    val emailAddress: ConfirmedEmailAddressAddress,
    val password: Password,
    val dataUsageOptIn: DataUsageOptIn
): Customer()

data class UnverifiedCustomer(
    val firstName: FirstName,
    val middleName: Option<MiddleName>,
    val lastName: LastName,

    val emailAddress: UnconfirmedEmailAddressAddress,
    val password: Password,
    val dataUsageOptIn: DataUsageOptIn,

    val emailVerificationToken: EmailVerificationToken
): Customer()



fun verifyCustomer(customer: UnverifiedCustomer) {
    confirmEmail(customer.emailAddress, customer.emailVerificationToken)
}