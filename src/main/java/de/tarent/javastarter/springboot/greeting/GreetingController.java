package de.tarent.javastarter.springboot.greeting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class GreetingController {

    private final GreetingRepository repository;

    @Autowired
    public GreetingController(GreetingRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/greetings", produces = "application/json")
    public ResponseEntity<List<Greeting>> getAll() {
        return ResponseEntity.ok(repository.getAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/greetings/{id}", produces = "application/json")
    public ResponseEntity<Greeting> get(@PathVariable("id") long id) {
        return ResponseEntity.ok(repository.get(id));
    }
}
