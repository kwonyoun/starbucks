package com.example.starbucks.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.starbucks.dto.MenuDto;
import com.example.starbucks.service.MenuService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class ApiMenu {

    private final MenuService menuService;

    @GetMapping("/menu/load")
        public String loadMenu() {
            menuService.insertFromCsv("data/starbucks_menu_list.csv");
            return "메뉴 데이터 DB 저장 완료";
    }

    @GetMapping("menu")
    public List<MenuDto> getApiMenu() {
        List<MenuDto> menu = menuService.getMenus();
        return menu;
    }

}
