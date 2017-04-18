package deepdive.typesystem;

public class Primitives {

    // size not really defined
    boolean b = true;

    // One byte
    byte by = 0xf;

    // 16-bit unicode character
    char c = 'c';

    // 16-bit signed two's complement integer : -32,768 to 32,767
    short s = Short.MAX_VALUE;

    // 32-bit signed two's complement integer : -2^31 and a maximum value of 2^31-1
    int i = Integer.MAX_VALUE;

    // 64-bit signed two's complement integer : -2^63 and a maximum value of 2^63-1
    long l = Long.MAX_VALUE;

    // 32-bit IEEE single-precision 754 floating point.
    float f = Float.MAX_VALUE;

    // double-precision 64-bit IEEE 754 floating point.
    double d = Double.MAX_VALUE;
}
