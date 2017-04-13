package deepdive.final_transient_volatile;

// tag::class[]
class Lifecycle {

    /**
     * 1 - Static content is initialized first.
     */
    static int i = 0;
    static {
        System.out.println("Hello Static");
    }

    /**
     * 2 - Fix attributes are initialized.
     */
    final int z = 10;

    {
        System.out.println("Hello Object");
    }

    final int y;

    /**
     * 3 - The constructor is invoked and only one time.
     */
    Lifecycle() {
        y = 5;
        System.out.println("Hello constructor");
    }

    /**
     * 4 - After a common time, the finalize method is invoked to destroy the object.
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Object destroyed");
        super.finalize();
    }
    // end::class[]

    public static void main(String[] args) throws InterruptedException {

        new Lifecycle();
        
        for(long i = 0; i < 444444L; i++) {
            Object x = new Lifecycle();
            x = null;
        }

        System.out.println("main end");
    }
}
