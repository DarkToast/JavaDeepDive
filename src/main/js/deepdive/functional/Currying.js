//tag::currying[]
const add = (x, y) => x + y;

const curryAdd = x => y => x + y;

add(3, 4);      // 7
curryAdd(3)(4); // 7
//end::currying[]



//tag::curry-factory-data[]
const devMode  = true;

let prodEnvironment = {
    customerUrl: "farfar.away.prod.host:9010"
};

let testEnvironment = {
    customerUrl: "localhost:9010"
};
//end::curry-factory-data[]



//tag::curry-factory-oldway[]
let api = {
    getCustomer: (environment, customer) => {
        console.log("GET on " + environment.customerUrl + "/" + customer);
    }
};

api.getCustomer(prodEnvironment, "max.mustermann");
api.getCustomer(prodEnvironment, "bärbel.beispiel");
//end::curry-factory-oldway[]



//tag::curry-factory[]
// Constructing a new function
const getCustomerCurry = environment => customer => {
    console.log("GET on "+ environment.customerUrl + "/" + customer);
};

api = {
    getCustomer: devMode ? getCustomerCurry(testEnvironment) : getCustomerCurry(prodEnvironment)
};

api.getCustomer("max.mustermann");
api.getCustomer("bärbel.beispiel");
//end::curry-factory[]



//tag::curry-closure[]
const createApi = envMode => {
    return {
        getCustomer: envMode ? getCustomerCurry(testEnvironment) : getCustomerCurry(prodEnvironment)
    };
};

const curryApi = createApi(devMode);

curryApi.getCustomer("max.mustermann");
curryApi.getCustomer("bärbel.beispiel");
//end::curry-closure[]