// simple_functor
const Functor = func => from => func(from);

let functor = Functor(x => x * x);

functor(2);       // 4




// array_functor
const ArrayFunctor = func => fromArray => {
    let toArray = [];

    for (let i = 0; i < fromArray.length; i++) {
        toArray[i] = func(fromArray[i]);
    }

    return toArray;
};

let aFunctor = ArrayFunctor(x => x * x);

aFunctor([1, 2, 3, 4]);        // [ 1, 4, 9, 16 ]



// customer_functor
const CustomerFunctor = func => fromCustomer => {
    return func(fromCustomer);
};

let customer = {
    contract:  "contractStub",
    getContract: () => this.contract
};

// Can return null. So, our problem is no solved.
let cFunctor = CustomerFunctor(customer => customer.getContract());

cFunctor(customer);