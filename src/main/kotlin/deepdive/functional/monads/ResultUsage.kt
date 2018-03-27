package deepdive.functional.monads

import com.fasterxml.jackson.databind.ObjectMapper


// Domain model
data class BaseFee(val value: Float)

data class Tariff(val baseFee: BaseFee)

data class Contract(val tariff: Tariff)

data class Customer(val contract: Contract)



// Data validation
data class ValidationFailure(val failure: String)

data class ValidationFailed(val failures: List<ValidationFailure>)



// Http response stuff
data class HttpStatus(val value: Int)

data class HttpResponse<out T>(val httpStatus: HttpStatus, val entity: T)



// Business functions:
fun getBaseFee(tariff: Tariff): Result<BaseFee, ValidationFailed> = Success(tariff.baseFee)

fun getTariff(contract: Contract): Result<Tariff, ValidationFailed> = Success(contract.tariff)

fun getContract(customer: Customer): Result<Contract, ValidationFailed> = Success(customer.contract)



// Mapping function -> Business rules etc.
val customerToTariffBaseFee: (Customer) -> Result<BaseFee, ValidationFailed> = { customer ->
    getContract(customer)
            .flatMap { contract -> getTariff(contract) }
            .flatMap { tariff -> getBaseFee(tariff) }
}



// Helper function as "get entity from ... db, http, ... etc." side effect.
fun getCustomer(): Customer = Customer(Contract(Tariff(BaseFee(12.99F))))


// The overall application.
fun main(args: Array<String>) {
    val customerInput = getCustomer()

    val responseOutput: HttpResponse<String> =
            Result.of<Customer, ValidationFailed>(customerInput)
                    .flatMap(customerToTariffBaseFee)                                   // Returns `Result<BaseFee, ValidationFailed>`
                    .map { baseFee ->
                        val json = ObjectMapper().writer().writeValueAsString(baseFee)
                        HttpResponse(HttpStatus(200), json)
                    }                                                                   // Returns `Result<HttpResponse, ValidationFailed>`
                    .recover { failure ->
                        val json = ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(failure)
                        Success(HttpResponse(HttpStatus(400), json))
                    }                                                                   // Returns `Result<HttpResponse, ValidationFailed>`
                    .getOrElse(HttpResponse(HttpStatus(500), "Unknown error occurred"))

    println(responseOutput)
}

/*
    Failure(ValidationFailed(listOf(
        ValidationFailure("contract has no tariff"),
        ValidationFailure("contract is expired")
    )))
*/