//tag::functional_list[]
/**
 *  Like `selectFirst`. This function always returns the actual `value`
 * @param value
 * @returns {function(*): *}
 */
const head = value => tail => value;

/**
 *  Like `selectSecond`. This function always returns the list tail.
 * @param value
 * @returns {function(*): *}
 */
const tail = value => tail => tail;

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
//end::functional_list[]



//tag::functional_list-usage[]
const myList = list(10)(list(15)(list(9)(nil)));
const concatenated = list(20)(myList);


const value1 = concatenated(head);              // 20
const value2 = concatenated(tail)(tail)(head);  // 15
//end::functional_list-usage[]




//tag::functional_list_length[]
/**
 * A convenience method to get the length of the list.
 *   Iterates the list recursively and increments a counter.
 *   It uses inner helper function to use tail recursion.
 *
 * @param list
 * @returns {*}
 */
const length = list => {
    const step = (sum, tailList) => {
        return tailList === nil ? sum : step(sum + 1, tailList(tail));
    };

    return step(0, list);
};

const lengthList = list(1)(list(2)(nil));
console.log("Length " + length(lengthList));
//end::functional_list_length[]




//tag::functional_list_get[]
/**
 * A more convenient method to access the list, is this
 * recursive method:
 * @param list
 * @param index
 * @returns {*}
 */
const get = (list, index) => {
    if(index <= 0) {
        return list(head)
    } else {
        return get(list(tail), index - 1)
    }
};

const getList = list(3)(list(4)(nil));

get(getList, 1);    // 4
//end::functional_list_get[]




//tag::functional_list_append[]
/**
 * This function concatenates two list.
 *   In recursive steps it creates a new list with all elements of `list1` till the last element is `nil`
 *   and then adds the whole `list2` to the end.
 * @param list1
 * @param list2
 * @returns {*}
 */
const append = (list1, list2) => {
    if(list1 === nil) {
        return list2
    } else {
        return list (list1(head)) (append(list1(tail), list2))
    }
};

const firstList = list(1)(list(2)(nil));
const secondList = list(3)(list(4)(nil));

const appendedList = append(firstList, secondList);
//end::functional_list_append[]




/// ???
const add = value => {
    const step = myList => element => {
        if(element == null) {
            return myList
        } else {
            return step(list(element)(myList))
        }
    };

    return step(nil)(value)
};

const myList2 = add(1)(2)(3)(4)(null);

console.log(get(myList2,0));