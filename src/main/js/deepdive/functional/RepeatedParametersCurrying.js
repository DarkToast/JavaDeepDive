
const sum = x => {
    return y => y === null ?
        x + y :
        sum(x +y );
};

const result = sum(0)(1)(2)(3)(4)(null);   // returns 10