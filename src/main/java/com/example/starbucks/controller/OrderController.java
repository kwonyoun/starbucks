package com.example.starbucks.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.starbucks.dto.StoreDto;
import com.example.starbucks.service.MenuService;
import com.example.starbucks.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequiredArgsConstructor
@RequestMapping("order")
@Slf4j
public class OrderController {

    private final OrderService orderService;
    private final MenuService menuService;

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

    @GetMapping("store/{id}")
    public String getStoreDetail(@PathVariable int id, Model model) {
        StoreDto store = orderService.getStoreById(id);
        log.info("getStoreById = {}", store);
        model.addAttribute("store", store);
        return "storeDetail";
    }

    @GetMapping("menu")
    public String menuPage(
            @RequestParam int storeId,
            @RequestParam String orderType,
            Model model) {

        StoreDto store = orderService.getStoreById(storeId);
        model.addAttribute("store", store);
        model.addAttribute("orderType", orderType);
        model.addAttribute("menuList", menuService.getMenus());

        log.info("storeId = {}", storeId);
        log.info("store = {}", store);
        // log.info("menulist = {}", menuService.getMenus());

        return "menu";
    }

    @PostMapping("cart/add")
    public String postMethodName(@RequestBody String entity) {
        //TODO: process POST request
        
        return entity;
    }
    


    
}
