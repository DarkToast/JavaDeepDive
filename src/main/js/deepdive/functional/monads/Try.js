// Motivation

const findCustomer = customerId =>  {
    throw "Customer "+ customerId + " not found!"
};

// Try.. catch.. is a side effect
// Immutability is impossible:
try {
    const myCustomer = findCustomer(4711);
} catch(err) {
    // ... ???
}



const Success = value => {
    return {
        // T => U
        map: mapFunction => Try( _ => mapFunction(value) ),

        // T => M[U]
        flatMap: flatMapFunction => flatMapFunction(value),

        isSuccess: () => true,
        isFailure: () => false,

        getOrElse: fallbackValue => value,

        recover: recoverFunction => Success(value)
    }
};

const Failure = error => {
    return {
        // T => U
        map: mapFunction => Failure(error),

        // T => M[U]
        flatMap: flatMapFunction => Failure(error),

        isSuccess: () => false,
        isFailure: () => true,

        getOrElse: fallbackValue => fallbackValue,

        recover: recoverFunction => Try( _ => recoverFunction(error) )
    }
};

export const Try = runnable => {
    try {
        return Success(runnable())
    } catch(err) {
        return Failure(err)
    }
};