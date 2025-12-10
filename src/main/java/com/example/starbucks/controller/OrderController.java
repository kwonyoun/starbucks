package com.example.starbucks.controller;

import org.springframework.stereotype.Controller;

import com.example.starbucks.service.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String getOrderPage() {
        return "order";
    }
    

    
}
