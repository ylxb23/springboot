package org.zero.boot.learn.feign.facade;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/service")
public interface MyService {
    @GetMapping("/hello")
    ResponseEntity<String> sayHello(@RequestParam(value = "name") String name);
}
