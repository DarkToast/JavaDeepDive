import test from 'ava';

import { Try }
    from "../../../../main/js/deepdive/functional/monads/Try";


// Test data
const contract = {
    getBaseFee: () => 12.99
};

const customer = {
    getContract: () => contract
};

const findCustomerSuccess = id => customer;
const findCustomerFailure = id => { throw`Customer ${id} not found`; };



test('A successful call is a Success', async (t) => {
    t.is(Try( _ => findCustomerSuccess(1) ).isSuccess(), true);
    t.is(Try( _ => findCustomerSuccess(1) ).isFailure(), false);
});

test('A failed call is a Failure', async (t) => {
    t.is(Try( _ => findCustomerFailure(1) ).isSuccess(), false);
    t.is(Try( _ => findCustomerFailure(1) ).isFailure(), true);
});

test('a Success can be mapped', async (t) => {
    const baseFeeTry = Try( _ => findCustomerSuccess(1) )
        .map(c => c.getContract())
        .map(con => con.getBaseFee());

    t.is(baseFeeTry.isSuccess(), true);
    t.is(baseFeeTry.getOrElse(-1), 12.99);
});

test('Try has a circuit breaker', async (t) => {

    const baseFeeTry = Try( _ => findCustomerFailure(1) )
        .map(c => c.getContract())
        .map(con => { throw "foo" });

    t.is(baseFeeTry.isFailure(), true);
    t.is(baseFeeTry.getOrElse(-1), -1);
});

test('Failed Try can be recovered', async (t) => {

    const baseFeeTry = Try( _ => findCustomerFailure(1) )
        .map(c => c.getContract())
        .map(con => { throw "foo" })
        .recover(err => 28);

    t.is(baseFeeTry.isSuccess(), true);
    t.is(baseFeeTry.getOrElse(-1), 28);
});

test('Success Try can be recovered, but it has no effects', async (t) => {

    const baseFeeTry = Try( _ => findCustomerSuccess(1) )
        .map(c => c.getContract())
        .map(con => con.getBaseFee())
        .recover(err => 28);

    t.is(baseFeeTry.isSuccess(), true);
    t.is(baseFeeTry.getOrElse(-1), 12.99);
});