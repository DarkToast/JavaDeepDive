package de.tarent.javastarter.springboot.greeting;

import de.tarent.javastarter.springboot.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class GreetingRepositorySpec {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    GreetingRepository repository;

    @Test
    public void aRepoCanListAllBasicGreetings() {
        //expect
        assertEquals(2, repository.getAll().size());
    }

    @Test
    public void aRepoCanListASingleGreeting() {
        // when
        Greeting greeting = repository.get(1);

        //then
        assertEquals(1, greeting.getId());
        assertEquals("Hallo Welt", greeting.getGreeting());
    }

    @Test
    public void aRepoCanListAnotherGreeting() {
        // when
        Greeting greeting = repository.get(2);

        //then
        assertEquals(2, greeting.getId());
        assertEquals("Hello World", greeting.getGreeting());
    }

    @Test
    public void aRepoReceivesANullOnANotExistingId() {
        // when
        Greeting greeting = repository.get(4711);

        //then
        assertNull(greeting);
    }

}