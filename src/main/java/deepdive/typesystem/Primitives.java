package deepdive.typesystem;

public class Primitives {

    // tag::without_class[]
    // size not really defined
    boolean b = true;
    Boolean bo = b;

    // One byte
    byte by = 0xf;
    Byte byo = by;

    // 16-bit unicode character
    char c = 'c';
    Character co = c;

    // 16-bit signed two's complement integer : -32,768 to 32,767
    short s = Short.MAX_VALUE;
    Short so = s;

    // 32-bit signed two's complement integer : -2^31 and a maximum value of 2^31-1
    int i = Integer.MAX_VALUE;
    Integer io = i;

    // 64-bit signed two's complement integer : -2^63 and a maximum value of 2^63-1
    long l = Long.MAX_VALUE;
    Long lo = l;

    // 32-bit IEEE single-precision 754 floating point.
    float f = Float.MAX_VALUE;
    Float fo = f;

    // double-precision 64-bit IEEE 754 floating point.
    double d = Double.MAX_VALUE;
    Double dO = d;

    // native typed arrays
    int[] iArray = new int[5];
    Object[] oArray = new Object[5];
    // end::without_class[]

    
    // tag::autoboxing[]
    public void boxing(int i) {
        Integer ii = i;

        // But this doesn't work. So autoboxing works not everywhere for every literal.
        //System.out.println(2 instanceof Integer);
    }

    public void unboxing(Integer i) {
        int ii = i;
    }
    // end::autoboxing[]
}
