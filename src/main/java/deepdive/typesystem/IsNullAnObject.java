package deepdive.typesystem;

public class IsNullAnObject {

    // int i = null;    <-- Won't work

    // Null seems not to be a primitive type. So it must be some kind of object.
    Integer ii = null;

    public static void main(String[] args) {
        new IsNullAnObject();
    }

    public IsNullAnObject() {
        System.out.println(ii instanceof Object);
        System.out.println("foo" instanceof Object);
        System.out.println(null instanceof Object);
    }
}
