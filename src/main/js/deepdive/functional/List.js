//tag::functional_list[]
/**
 *  Like `selectFirst`. This function always returns the actual `value`
 * @param value
 * @returns {function(*): *}
 */
const element = value => tail => value;

/**
 *  Like `selectSecond`. This function always returns the list tail.
 * @param value
 * @returns {function(*): *}
 */
const next = value => tail => tail;

/**
 * We have to define a termination element of the list. In our systems it's a
 * function with element `null` and a tail of itself.
 * @param func
 * @returns {*}
 */
const nil = func => func(null)(nil);

/**
 * Our list function. Very similar to `makePair` this function tales a
 * `tail` function as `second` element, which represents all other list elements.
 *
 * @param value
 * @returns {function(*=): function(*): *}
 */
const list = value => tail => func => func(value)(tail);


// --- Usage

const myList = list(10)(list(15)(list(9)(nil)));
const concatenated = list(20)(myList);


const value1 = concatenated(element);              // 20
const value2 = concatenated(next)(next)(element);  // 15

/**
 * A more convenient method to access the list, is this
 * recursive method:
 * @param list
 * @param index
 * @returns {*}
 */
const get = (list, index) => {
    if(index <= 0) {
        return list(element)
    } else {
        return get(list(next), index - 1)
    }
};

const value3 = get(myList, 2);    // 9
console.log(value1 + " " + value2 + " " + value3);
//end::functional_list[]