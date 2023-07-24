package org.zero.boot.learn.feign.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.zero.boot.learn.feign.facade.MyService;

@Service
public class MyServiceImpl implements MyService {
    @Override
    public ResponseEntity<String> sayHello(String name) {
        String res = "Hello, " + name;
        return ResponseEntity.ok(res);
    }
}
