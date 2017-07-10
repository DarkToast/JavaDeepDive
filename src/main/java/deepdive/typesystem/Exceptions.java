package deepdive.typesystem;

public class Exceptions {

    public Exceptions() {
        uncheckedException();

        try {
            rethrow();
        } catch (Exception e) {
            System.out.println("I catched the exception" + e.getMessage());
        }
    }

    private void rethrow() throws Exception {
        checkedException();
    }

    private void checkedException() throws Exception {
        throw new Exception("checked");
    }

    private void uncheckedException() {
        throw new RuntimeException("unchecked");
    }

}
