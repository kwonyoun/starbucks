package com.example.starbucks.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.starbucks.dto.CartItemDto;
import com.example.starbucks.dto.StoreDto;
import com.example.starbucks.service.MenuService;
import com.example.starbucks.service.OrderService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("confirm")
    public String confirmOrder(HttpSession session, Model model) {

        List<CartItemDto> cart =
                (List<CartItemDto>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            return "redirect:/cart";
        }

        // int totalPrice = cart.stream()
        //         .mapToInt(CartItemDto::getTotalPrice)
        //         .sum();

        model.addAttribute("cart", cart);
        // model.addAttribute("totalPrice", totalPrice);

        return "orderConfirm";
    }

    @GetMapping("/confirm")
    public String getOrderConfirmPage() {
        return "orderConfirm";
    }

    @PostMapping("complete")
    public String completeOrder(HttpSession session) {

        List<CartItemDto> cart =
                (List<CartItemDto>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            return "redirect:/cart";
        }

        orderService.saveOrder(cart);

        session.removeAttribute("cart"); // ⭐ 장바구니 비우기

        return "redirect:/order/complete/result";
    }

    @GetMapping("complete/result")
    public String orderCompletePage() {
        return "orderComplete";
    }


    

}
