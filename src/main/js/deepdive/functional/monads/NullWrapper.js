
//tag::null_wrapper[]
const None = {
    get: () => null,
    isPresent: () => false
};

const Some = value => {
    return {
        get: () => value,
        isPresent: () => false
    }
};

const Option = value => value != null ? Some(value) : None;
//end::null_wrapper[]


//tag::usage[]
const o = Option("Hallo Welt");

if(o.isPresent()) {
    console.log(o.get())
}
//end::usage[]


//tag::alternative_usage[]
switch(o) {
    case None:
        console.log("Oh no!");
        break;
    default:
        console.log(o.get());
        break;
}
//end::alternative_usage[]