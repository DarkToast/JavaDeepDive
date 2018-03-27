
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




const o = Option("Hallo Welt");

if(o.isPresent()) {
    console.log(o.get())
}



switch(o) {
    case None:
        console.log("Oh no!");
        break;
    default:
        console.log(o.get());
        break;
}