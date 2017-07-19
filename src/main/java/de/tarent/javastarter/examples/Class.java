package de.tarent.javastarter.examples;

class Class implements IntGetter {
    private int i;
    protected String s;

    // constructor
    public Class(int i, String s) {
        this.i = i;
        this.s = s;

        counter++;
    }

//    // method
    public int getI() {
        int y = i + 4;
        return y;
    }



    private static int counter = 0;

    static int getCounter() {
        return counter;
    }

}
