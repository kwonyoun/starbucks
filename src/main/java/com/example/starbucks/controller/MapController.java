package com.example.starbucks.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("store")
public class MapController {

    @Value("${kakao.javascript.key}")
    private String kakaoKey;

    @GetMapping("map")
    public String getStoreMap(Model model) {
        model.addAttribute("kakaoKey", kakaoKey);
        return "storeMap";
    }
    
}
