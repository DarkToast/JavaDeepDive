
// tag::classic[]
function add(x, y) {
    return x + y
}

add(1, 2); // 3
// end::classic[]



// tag::f_as_val[]
const fAdd = function add(x, y) {
    return x + y
};

const fAdd2 = (x, y) => {
    return x + y;
};

fAdd(1, 2);
fAdd2(1, 2);
// end::f_as_val[]



// tag::f_as_param[]
const usingAFunction = (addFunction) => {
    return addFunction(4, 8);
};

usingAFunction(fAdd); // 12;
// end::f_as_param[]