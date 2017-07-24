package de.tarent.javastarter.springboot.greeting;

class Greeting {
    private long id;

    private String greeting;

    public Greeting() {
    }

    Greeting(long id, String greeting) {
        this.id = id;
        this.greeting = greeting;
    }

    public long getId() {
        return id;
    }

    public String getGreeting() {
        return greeting;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
}
