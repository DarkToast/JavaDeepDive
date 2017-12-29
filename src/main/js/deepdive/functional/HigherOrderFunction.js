
//tag::function_builder[]
const greaterThan = x => {
    return (y) => x > y
};

const f = greaterThan(10);
f(11); // true
//end::function_builder[]



//tag::function_parameter[]
const mapX = (x, xMapper) => {
    return xMapper(x)
};

mapX(10, i => i * i);
//end::function_parameter[]



//tag::lib_example[]
[1, 2, 3].map(i => i + 2);
//end::lib_example[]