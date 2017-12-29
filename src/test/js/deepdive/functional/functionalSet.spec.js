import test from 'ava';

import Sets from '../../../../main/js/deepdive/functional/functionalSet';

// --- Test data ---
const a = 1;
const b = 5;
const c = 10;

const unknown = 0;

const aSet = Sets.singleSet(a);
const bSet = Sets.singleSet(b);
const cSet = Sets.singleSet(c);
// -----------------


test('an empty set has no a element', async (t) => {
    t.is(Sets.emptySet(a), false);
});


test('a single set has the a element', async (t) => {
    let set = Sets.singleSet(a);

    t.is(set(a), true);
    t.is(set(unknown), false);
});


test('contains checks for an element existence', async (t) => {
    let set = Sets.singleSet(a);

    t.is(Sets.contains(set, a), true);
    t.is(Sets.contains(set, unknown), false);
});


test('union constructs a union set', async (t) => {
    let unionSet = Sets.union(aSet, bSet);

    t.is(unionSet(a), true);
    t.is(unionSet(b), true);
    t.is(unionSet(unknown), false);
});


test('a union of several sets has all elements', async (t) => {
    let unionSet = Sets.union(aSet, Sets.union(bSet, cSet));

    t.is(unionSet(a), true);
    t.is(unionSet(b), true);
    t.is(unionSet(c), true);
});


test('an intersect contains all elements which are in both sets', async (t) => {
    let setAB = Sets.union(aSet, bSet);
    let setCB = Sets.union(cSet, bSet);

    let intersectSet = Sets.intersect(setAB, setCB);

    t.is(intersectSet(a), false);
    t.is(intersectSet(b), true);
    t.is(intersectSet(c), false);
    t.is(intersectSet(unknown), false);
});


test('an intersect contains no elements if both sets have no some equal elements', async (t) => {
    let setAB = Sets.union(aSet, bSet);

    let intersectSet = Sets.intersect(setAB, cSet);

    t.is(intersectSet(a), false);
    t.is(intersectSet(b), false);
    t.is(intersectSet(c), false);
});


test('a diff contains all elements which are NOT in both sets', async (t) => {
    let setAB = Sets.union(aSet, bSet);
    let setCB = Sets.union(cSet, bSet);

    let diffSet = Sets.diff(setAB, setCB);

    t.is(diffSet(a), true);
    t.is(diffSet(b), false);
    t.is(diffSet(c), true);
    t.is(diffSet(unknown), false);
});


test('a diff contains all elements if both sets have no equal elements', async (t) => {
    let setAB = Sets.union(aSet, bSet);

    let diffSet = Sets.diff(setAB, cSet);

    t.is(diffSet(a), true);
    t.is(diffSet(b), true);
    t.is(diffSet(c), true);
});


test('filter returns a subset of the elements which matches the filter', async (t) => {
    let setABC = Sets.union(aSet, Sets.union(bSet, cSet));

    let filterSet = Sets.filter(setABC, i => i >= b);

    t.is(filterSet(a), false);
    t.is(filterSet(b), true);
    t.is(filterSet(c), true);
    t.is(filterSet(unknown), false);
});


test('filter can take every filter', async (t) => {
    let setABC = Sets.union(aSet, Sets.union(bSet, cSet));

    let filteredSet = Sets.filter(setABC, i => false);

    t.is(filteredSet(a), false);
    t.is(filteredSet(b), false);
    t.is(filteredSet(c), false);
});
