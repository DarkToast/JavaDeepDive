import test from 'ava';

import { Option }
    from "../../../../main/js/deepdive/functional/monads/Option";


// Test data
const tariff = {
    getBaseFee: () => 12.99
};

const contract = {
    getTariff: () => tariff
};

const customer = {
    getContract: () => contract
};



test('an empty optional is not present', async (t) => {
    t.is(Option(null).isPresent(), false);
});

test('an valued optional is not present', async (t) => {
    t.is(Option("Hallo Welt").isPresent(), true);
});

test('an valued optional can be mapped', async (t) => {
    const baseFeeOption = Option(customer)
        .map(c => c.getContract())
        .map(con => con.getTariff())
        .map(tar => tar.getBaseFee());

    t.is(baseFeeOption.isPresent(), true);
    t.is(baseFeeOption.getOrElse(-1), 12.99);
});

test('option has a circuit breaker', async (t) => {

    const baseFeeOption = Option(customer)
        .map(c => c.getContract())
        .map(con => null)
        .map(tar => tar.getBaseFee());

    t.is(baseFeeOption.isPresent(), false);
    t.is(baseFeeOption.getOrElse(-1), -1);
});