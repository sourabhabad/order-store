package com.planet.order.controller;

import com.planet.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Rest Controller
 */
@RestController
@RequestMapping("/store")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/start")
    public String startTxn(@RequestParam("file") MultipartFile file) {
        return orderService.processFile(file);

    }
}
