
// Let a `Set` be defined as a function `(int) => bool`

/**
 * Defines an empty set as a function, which returns for every element `i` false.
 * @param i
 * @returns {boolean}
 */
const emptySet = i => false;

/**
 * This function returns a function, which tests the given value `element` to the parameter `i`.
 * @param element
 * @returns {function(*): boolean}
 */
const singleSet = element => i => i === element;

/**
 * Contains tests `element` if it is in a given `set`.
 * @param set
 * @param element
 * @returns {*}
 */
const contains = (set, element) => undefined;

/**
 * Union returns a new set, which contains all elements of `aSet` and `bSet`.
 * @param aSet
 * @param bSet
 * @returns {function(*=): *}
 */
const union = (aSet, bSet) => undefined;

/**
 * Intersect returns a new set, which contains all element that are in both sets `aSet` and `bSet`.
 * @param aSet
 * @param bSet
 * @returns {function(*=): *}
 */
const intersect = (aSet, bSet) => undefined;

/**
 * Diff returns a new set, which contains all elements, which are not in both sets `aSet` and `bSet`.
 * @param aSet
 * @param bSet
 * @returns {function(*=): boolean}
 */
const diff = (aSet, bSet) => undefined;

/**
 * Filter returns a new set, which contains all elements that matches the given filter function.
 * As we operate with functional sets, we have to define an upper and lower bound in which we can let operate the filter
 * application on the sets.
 *
 * @param set: Set
 * @param filter: `int => bool`
 * @returns {undefined}
 */
const bound = 1000;
const filter = (set, filter) => undefined;


module.exports.emptySet = emptySet;
module.exports.singleSet = singleSet;
module.exports.contains = contains;
module.exports.union = union;
module.exports.intersect = intersect;
module.exports.diff = diff;
module.exports.filter = filter;