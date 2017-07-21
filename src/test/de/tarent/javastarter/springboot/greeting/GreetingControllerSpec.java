package de.tarent.javastarter.springboot.greeting;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoRule;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class GreetingControllerSpec {
    GreetingRepository repository;

    GreetingController controller;

    @Before
    public void setup() {
        repository = mock(GreetingRepository.class);
        controller = new GreetingController(repository);
    }

    @Test
    public void AControllerReturnsAnHttp200OnListing() {
        // expect
        assertEquals(HttpStatus.OK, controller.getAll().getStatusCode());
    }

    @Test
    public void AControllerReturnsAllRepoObjects() {
        // given: A list of mocked greetings
        when(repository.getAll()).thenReturn(Collections.singletonList(new Greeting(0, "Hallo")));

        // when: We call the controller
        List<Greeting> greetings = controller.getAll().getBody();

        // expect
        assertEquals(1, greetings.size());
        assertEquals(0, greetings.get(0).id);
        assertEquals("Hallo", greetings.get(0).greeting);
    }

    @Test
    public void AControllerReturnsAnHttp200OnSingleCall() {
        // expect
        assertEquals(HttpStatus.OK, controller.get(0).getStatusCode());
    }

    @Test
    public void AControllerReturnsASingleRepoObject() {
        // given: A single greeting
        when(repository.get(0)).thenReturn(new Greeting(0, "Hallo"));

        // when: We call the controller
        Greeting greeting = controller.get(0).getBody();

        // expect
        assertEquals(0, greeting.id);
        assertEquals("Hallo", greeting.greeting);
    }

}