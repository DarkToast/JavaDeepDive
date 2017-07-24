package de.tarent.javastarter.springboot.greeting;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import static org.junit.Assert.*;

public class SimpleControllerSpec {

    private SimpleController simpleController = new SimpleController();

    @Test
    public void aSimpleControllerPlainReturnsHttp200() {
        // expect
        assertEquals(HttpStatus.OK, simpleController.getPlain().getStatusCode());
    }

    @Test
    public void aSimpleControllerPlainReturns_HalloWelt() {
        // expect
        assertEquals("Hallo Welt", simpleController.getPlain().getBody());
    }

    @Test
    public void aSimpleControllerJsonReturnsHttp200() {
        // expect
        assertEquals(HttpStatus.OK, simpleController.getPlain().getStatusCode());
    }

    @Test
    public void aSimpleControllerJsonReturnsAGreeting() {
        // expect
        assertEquals("Hallo Welt", simpleController.getJson().getBody().getGreeting());
    }


}