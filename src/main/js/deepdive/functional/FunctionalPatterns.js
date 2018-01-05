//tag::validation[]
/**
 *  Creates a max Length validator
 * @param length
 * @returns {function(*): boolean}
 */
const maxLength = length => value => value.length <= length;

/**
 * Creates a min length validator.
 * @param length
 * @returns {function(*): boolean}
 */
const minLength = length => value => value.length >= length;

/**
 * Creates a validator based on a custom function
 * @param matchFunction
 * @returns {function(*=): *}
 */
const matchCustom = matchFunction => value => matchFunction(value);

/**
 * Combines two boolean functions to one function.
 * @param f1
 * @param f2
 * @returns {function(*=): *}
 */
const combine = (f1, f2) => value => f2(value) && f1(value);


const lengthValidator = combine(minLength(5), maxLength(10));
const validator = combine(lengthValidator, matchCustom(v => v.startsWith('Ha')));


console.log(validator("Hallo"));                // true
console.log(validator("Hall"));                 // false    minLength
console.log(validator("HalloHalloHallo"));      // false    maxLength
console.log(validator("hallo"));                // false    custom
//end::validation[]