package com.example.starbucks.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.starbucks.dto.CartItemDto;
import com.example.starbucks.dto.MenuDto;
import com.example.starbucks.dto.StoreDto;
import com.example.starbucks.service.MenuService;
import com.example.starbucks.service.OrderService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("cart")
public class CartController {

    private final OrderService orderService;
    private final MenuService menuService;
    
    @PostMapping("add")
    public String addToCart(
        @RequestParam int storeId,
        @RequestParam int menuId,
        @RequestParam String orderType,
        HttpSession session) 
    {        
        // 1️⃣ 기존 장바구니 가져오기
    List<CartItemDto> cart =
            (List<CartItemDto>) session.getAttribute("cart");

    if (cart == null) {
        cart = new ArrayList<>();
    }
    // 2️⃣ 매장 / 메뉴 정보 조회
    StoreDto store = orderService.getStoreById(storeId);
    MenuDto menu = menuService.findMenuById(menuId);

    // 3️⃣ 이미 담긴 메뉴인지 체크
    for (CartItemDto item : cart) {
        if (item.getMenuId() == menuId) {
            item.setQuantity(item.getQuantity() + 1);
            session.setAttribute("cart", cart);
            return "redirect:/cart";
        }
    }

    // 4️⃣ 새 항목 추가
    CartItemDto item = new CartItemDto();
    item.setStoreId(store.getId());
    item.setStoreName(store.getStoreName());
    item.setMenuId(menu.getId());
    item.setMenuName(menu.getMenuName());
    // item.setPrice(menu.getPrice());
    item.setOrderType(orderType);
    item.setQuantity(1);

    cart.add(item);

    session.setAttribute("cart", cart);

        return "redirect:/cart";
    }

    @GetMapping
    public String cartPage(HttpSession session, Model model) {

        List<CartItemDto> cart =
                (List<CartItemDto>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
        }

        // int totalPrice = cart.stream()
        //         .mapToInt(item -> item.getPrice() * item.getQuantity())
        //         .sum();

        model.addAttribute("cart", cart);
        // model.addAttribute("totalPrice", totalPrice);

        return "cart";
    }

    
}
