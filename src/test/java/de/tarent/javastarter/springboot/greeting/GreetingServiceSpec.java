package de.tarent.javastarter.springboot.greeting;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import de.tarent.javastarter.springboot.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class GreetingServiceSpec {

    TestRestTemplate testRestTemplate = new TestRestTemplate();

    @LocalServerPort
    private int applicationPort;

    private String baseUrl;

    @Before
    public void setup() {
        baseUrl = "http://localhost:" + applicationPort;
    }

    @Test
    public void aGetOnPlainReturnsHttp200() {
        //when: we make a GET on /plain
        final ResponseEntity<String> entity = testRestTemplate.getForEntity(baseUrl + "/plain", String.class);

        //then: We get a Http 200
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void aGetOnPlainReturnsHaloWelt() {
        //when: we make a GET on /plain
        final ResponseEntity<String> entity = testRestTemplate.getForEntity(baseUrl + "/plain", String.class);

        //then: We get a Http 200
        assertEquals("Hallo Welt", entity.getBody());
    }

    @Test
    public void aGetOnJsonReturnsHttp200() {
        //when: we make a GET on /json
        final ResponseEntity<String> entity = testRestTemplate.getForEntity(baseUrl + "/json", String.class);

        //then: We get a Http 200
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    public void aGetOnJsonReturnsHalloWelt() {
        //when: we make a GET on /json
        final ResponseEntity<Greeting> entity = testRestTemplate.getForEntity(baseUrl + "/json", Greeting.class);

        //then: We get a greetings body
        assertEquals("Hallo Welt", entity.getBody().getGreeting());
    }

    @Test
    public void aGetOnGreetingsReturnsHttp200() {
        //when: we make a GET on /greetings
        final ResponseEntity<List> entity = testRestTemplate.getForEntity(baseUrl + "/greetings", List.class);

        //then: We get a Http 200
        assertEquals(HttpStatus.OK, entity.getStatusCode());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void aGetOnGreetingsReturnsAList() {
        //when: we make a GET on /greetings
        final ResponseEntity<List> entity = testRestTemplate.getForEntity(baseUrl + "/greetings", List.class);

        //then: We get a list of greetings
        List<Map> list = (List<Map>) entity.getBody();
        assertEquals(2, list.size());
        assertEquals(1, list.get(0).get("id"));
        assertEquals("Hallo Welt", list.get(0).get("greeting"));
    }

    @Test
    public void aGetOnGreetingsWithIdReturnsASingleGreeting() {
        //when: we make a GET on /greetings
        final ResponseEntity<Greeting> entity = testRestTemplate.getForEntity(baseUrl + "/greetings/2", Greeting.class);

        //then: We get a single greeting
        assertEquals(2, entity.getBody().getId());
        assertEquals("Hello World", entity.getBody().getGreeting());
    }

    @Test
    public void aGetOnAnUnknownGreetingIdReturnsHttp404() {
        //when: we make a GET on /greetings
        final ResponseEntity<Greeting> entity = testRestTemplate.getForEntity(baseUrl + "/greetings/unknown", Greeting.class);

        //then: We get a single greeting
        assertEquals(HttpStatus.NOT_FOUND, entity.getStatusCode());
    }

}