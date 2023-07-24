package org.zero.boot.learn.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class HeaderRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.header("X-Forward-For", "zero.com");
    }
}
