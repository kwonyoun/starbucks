package com.example.starbucks.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.starbucks.dto.StoreDto;
import com.example.starbucks.service.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public String getOrderPage() {
        return "order";
    }

    @GetMapping("list")
    @ResponseBody
    public List<StoreDto> getOrderList(
            @RequestParam double lat,
            @RequestParam double lng) {

        return orderService.getNearest(lat, lng);
    }
    
}
