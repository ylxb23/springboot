package org.zero.test.learn.feign;

import feign.Feign;
import org.springframework.http.ResponseEntity;
import org.zero.boot.learn.feign.HeaderRequestInterceptor;
import org.zero.boot.learn.feign.facade.MyService;

public class ClientApp {

    public static void main(String[] args) {
        MyService myService = Feign.builder()
                .requestInterceptor(new HeaderRequestInterceptor())
                .target(MyService.class, "127.0.0.1");
        ResponseEntity<String> res = myService.sayHello("Jan");
        System.out.println(res);
    }
}
