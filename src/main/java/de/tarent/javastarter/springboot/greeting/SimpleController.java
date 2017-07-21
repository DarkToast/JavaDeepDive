package de.tarent.javastarter.springboot.greeting;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SimpleController {

    @RequestMapping(method = RequestMethod.GET, path = "/plain", produces = "plain/text")
    public ResponseEntity<String> getPlain() {
        return ResponseEntity.ok("Hallo Welt");
    }

    @RequestMapping(method = RequestMethod.GET, path = "/json", produces = "application/json")
    public ResponseEntity<Greeting> getJson() {
        return ResponseEntity.ok(new Greeting(1, "Hallo Welt"));
    }

}
