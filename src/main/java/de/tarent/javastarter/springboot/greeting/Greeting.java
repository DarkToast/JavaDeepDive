package de.tarent.javastarter.springboot.greeting;

class Greeting {
    public final long id;

    public final String greeting;

    Greeting(long id, String greeting) {
        this.id = id;
        this.greeting = greeting;
    }
}
