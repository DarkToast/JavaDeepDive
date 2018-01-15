
export const Optional = (value) => {
    return value == null ? None : Some(value)
};

const Some = (value) => {
    return {
        bind: (bindFunction) => bindFunction(value),
        map: (mapFunction) => Optional(mapFunction(value)),
        get: () => value
    }
};

const None = {
    bind: (bindFunction) => None,
    map: (mapFunction) => None,
    get: () => null
};


console.log(Optional("Hallo Welt").map(v => v.substr(0, 4)).get());
console.log(Optional(null).map(v => v.substr(0, 4)).get());