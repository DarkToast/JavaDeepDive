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
//end::validation[]




//tag::validation-combination[]
/**
 * Combines two boolean functions to one function.
 * @param f1
 * @param f2
 * @returns {function(*=): *}
 */
const combine = (f1, f2) => value => f2(value) && f1(value);
//end::validation-combination[]




//tag::validation-usage[]
const min = minLength(5);
const max = maxLength(10);
const hallo = matchCustom(v => v.startsWith('Ha'));


const lengthValidator = combine(min, max);
const validator = combine(lengthValidator, hallo);


validator("Hallo");                // true
validator("Hall");                 // false    minLength
validator("HalloHalloHallo");      // false    maxLength
validator("hallo");                // false    custom
//end::validation-usage[]