
// Some represents an existing value
const Some = (value) => {
    return {
        bind: bindFunction => bindFunction(value),
        map: mapFunction => Option(mapFunction(value)),
        get: () => value,

        isPresent: () => true,
        getOrElse: fallbackValue => value
    }
};

// Some represents the nil object
const None = {
    bind: bindFunction => None,
    map: mapFunction => None,
    get: () => null,

    isPresent: () => false,
    getOrElse: fallbackValue => fallbackValue
};

export const Option = (value) => {
    return value == null ? None : Some(value)
};