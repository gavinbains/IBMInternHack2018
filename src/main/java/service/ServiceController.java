package service;

import java.util.concurrent.atomic.AtomicLong;

import model.NewsResponseModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    private static final String template = "Hello, %s!";
    private final AtomicLong    counter  = new AtomicLong();

    @RequestMapping("/service")
    public NewsResponseModel hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new NewsResponseModel(counter.incrementAndGet(), String.format(template, name));
    }
}
