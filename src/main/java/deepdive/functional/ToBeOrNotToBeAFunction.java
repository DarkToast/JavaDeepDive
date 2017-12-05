package deepdive.functional;

@SuppressWarnings("unused")
public class ToBeOrNotToBeAFunction {

    // This is a function.
    // It has input parameter and one return value.
    // No side effects!
    public int add(int x, int y) {
        return x + y;
    }

    // ---------------

    private int y;

    // This is not a function. It has no return value, but only side effects.

    public void setY(int y) {
        this.y = y;
    }

    // This is also not a function, because the result is affected by an external value `y`.
    // Also it has a side effect to stdout. An external caller can not know about this fact.

    public int sideEffectedAdd(int x) {
        System.out.println("This is x: " + x);  // <-- Side effect to stdout
        return x + y;   // <-- Side effect from `y`. related on the call of `setY`.
    }

    // --------------

    // IO in functional programming?
    // Daher sind reine funktionale Sprachen eher selten. IO ist immer seiteneffektbehaftet.
    // z.B. bei Haskell die IO Monaden.
}
