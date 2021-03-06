//tag::data_structure[]
//tag::data_structure-select_first[]
/**
 * A function with parameter `first`, that returns
 *   a function with parameter `second`, which returns
 *     the `first` value.
 * @param first
 * @returns {function(*): *}
 */
const selectFirst = first => second => first;
//end::data_structure-select_first[]

//tag::data_structure-select_second[]
/**
 * A function with parameter `first`, that returns
 *   a function with parameter `second`, which returns
 *     the `second` value.
 * @param first
 * @returns {function(*): *}
 */
const selectSecond = first => second => second;
//end::data_structure-select_second[]


//tag::data_structure-select_usage[]
selectFirst(1)(2);   // returns 1
selectSecond(1)(2);  // returns 2
//end::data_structure-select_usage[]


//tag::data_structure-pair[]
/**
 * A function with parameter `first`, that returns
 *   a function with parameter `second`, that returns
 *     a function with parameter `func`, that
 *       calls `func` with value `first` and calls the returned function with the value `second`.
 * @param first
 * @returns {function(*=): function(*): *}
 */
const makePair = first => second => func => func(first)(second);

// --- Usage ---

const pair = makePair(5)(6);
const first = pair(selectFirst);     // 5
const second = pair(selectSecond);   // 6
//end::data_structure-pair[]

console.log(pair + " " + first + " " + second);
//end::data_structure[]