package com.client;

import org.springframework.web.bind.annotation.RequestMapping;

public interface AppController {
    @RequestMapping("/getEureka")
    String getData();
}
