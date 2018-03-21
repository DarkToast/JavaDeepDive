
// Some represents an existing value

// Value might be the type T
// An `Option[T]` is a monad of T or `M[T]`
const Some = (value) => {
    return {
        // T => U - with U as the new type after mapping
        map: mapFunction => Option(mapFunction(value)),

        // T => M[U]
        flatMap: flatMapFunction => flatMapFunction(value),

        get: () => value,

        isPresent: () => true,

        getOrElse: fallbackValue => value
    }
};

// Some represents the nil object
const None = {
    // T => U
    map: mapFunction => None,

    // T => M[U}
    flatMap: flatMapFunction => None,

    get: () => null,

    isPresent: () => false,

    getOrElse: fallbackValue => fallbackValue
};

export const Option = (value) => {
    return value == null ? None : Some(value)
};