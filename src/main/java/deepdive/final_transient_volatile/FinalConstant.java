package deepdive.final_transient_volatile;

// tag::constructor[]
class FinalConstant {

    final int i;

    public FinalConstant() {
        i = 4;  // <- or in the constructor, which is a special method.
                // This is the latest position where a final variable can be initialized.
    }

// end::constructor[]

    
    // tag::aConstantFinalValue[]
    void aConstantFinalValue() {
        final int x = 1; // Initialized immediately

        final int y;
        String s = "Some foo";
        y = 3;  // <- must be initialized in the same scope

        // y = 4;  <- not possible
    }
    // end::aConstantFinalValue[]
}
