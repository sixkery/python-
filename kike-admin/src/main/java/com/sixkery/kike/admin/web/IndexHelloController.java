package com.sixkery.kike.admin.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: sixkery
 * @date:2021/4/5
 */
@RestController
@RequestMapping("/api/v1")
public class IndexHelloController {

    @GetMapping("/index")
    public String index() {
        return "hello";

    }
}
