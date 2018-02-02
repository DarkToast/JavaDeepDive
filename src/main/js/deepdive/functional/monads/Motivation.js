
//tag::simple_object[]
let customer = {
    contract: {
        tariff: {
            baseFee: 12.99
        }
    }
};

// Concrete object. We know, that these fields are valid.
customer.contract.tariff.baseFee;
//end::simple_object[]



//tag::substitution[]
customer = {
    contract:  null,
    getContract: () => this.contract
};

let contract = {
    tariff: null,
    getTariff: () => this.tariff
};

let tariff = {
    baseFee: 12.99,
    getBaseFee: () => this.baseFee
};

// Substitution through objects and functions. But is every substitution valid?
customer.getContract().getTariff().getBaseFee();
//end::substitution[]


//tag::expectation[]
customer.getContract().getTariff().getBaseFee();    // =>

contract.getTariff().getBaseFee();                  // =>

tariff.getBaseFee();                                // =>

baseFee;                                            // =>

12.99
//end::expectation[]



//tag::reality[]
customer.getContract().getTariff().getBaseFee();    // =>

null.getTariff().getBaseFee();                      // ⚡ BÄM ⚡

// TypeError: Cannot read property 'getTariff' of undefined
//end::reality[]