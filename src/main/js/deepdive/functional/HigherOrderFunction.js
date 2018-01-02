//tag::function_parameter[]
const mapX = (x, xMapper) => {
    return xMapper(x)
};

mapX(10, i => i * i);
//end::function_parameter[]


//tag::lib_example[]
[1, 2, 3].map(i => i + 2);
//end::lib_example[]


//tag::higher_order_function[]
const getEqualFunction = () => {
  return (x, y) => x === y;
};

const equalFunction = getEqualFunction();
equalFunction(1, 2); // false
//end::higher_order_function[]


//tag::function_builder[]
const greaterThan = x => {
    return (y) => x > y
};

const f = greaterThan(10);
f(9);  // false
f(11); // true

const g = greaterThan(15);
g(11); // false
g(16); // true
//end::function_builder[]