package pl.budyn.other.model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.budyn.other.rest.Greetings;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by hlibe on 17.12.2016.
 */
@RestController
public class GreetingsRestController {
    private final String template = "Hello %s";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/rest")
    public Greetings greeting(@RequestParam(value="name", defaultValue = "World!") String name){
        return new Greetings(counter.incrementAndGet(), String.format(template, name));
    }
    @RequestMapping("/resource")
    public Map<String,Object> home() {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }
}
