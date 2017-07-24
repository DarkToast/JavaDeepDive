package de.tarent.javastarter.springboot.greeting;

import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GreetingControllerSpec {
    GreetingRepository repository;

    GreetingController controller;

    @Before
    public void setup() {
        // With the test library `Mockito`, we can generate a "mock" for concrete classes.
        // Mocks has the same methods, but may have another behaviour.
        repository = mock(GreetingRepository.class);
        controller = new GreetingController(repository);
    }

    @Test
    public void AControllerReturnsAnHttp200OnListing() {
        // expect
        assertEquals(HttpStatus.OK, controller.getAll().getStatusCode());
    }

    @Test
    public void AControllerReturnsAllRepoObjectsAsList() {
        // given: A list of mocked greetings
        // With `when` and `thenReturn`, we can control the behaviour of the mock:
        when(repository.getAll()).thenReturn(Collections.singletonList(new Greeting(0, "Hallo")));

        // when: We call the controller
        List<Greeting> greetings = controller.getAll().getBody();

        // expect
        assertEquals(1, greetings.size());
        assertEquals(0, greetings.get(0).getId());
        assertEquals("Hallo", greetings.get(0).getGreeting());
    }

    @Test
    public void AControllerReturnsAnHttp200OnSingleCall() {
        // expect
        assertEquals(HttpStatus.OK, controller.get(0).getStatusCode());
    }

    @Test
    public void AControllerReturnsASingleGreetingObject() {
        // given: A single greeting
        when(repository.get(0)).thenReturn(new Greeting(0, "Hallo"));

        // when: We call the controller
        Greeting greeting = controller.get(0).getBody();

        // expect
        assertEquals(0, greeting.getId());
        assertEquals("Hallo", greeting.getGreeting());
    }

}