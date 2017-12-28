
// tag::a_function[]
function add(x, y) {
    return x + y
}
// end::a_function[]


// tag::not_a_function[]
let y = 5;

function setY(newY) {
    y = newY;
}

function sideEffectFunction(x) {
    console.log("This is " + x);
    return x + y;
}
// end::not_a_function[]