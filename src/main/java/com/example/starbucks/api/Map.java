package com.example.starbucks.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.starbucks.dto.StoreDto;
import com.example.starbucks.service.MapSerivce;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api")
public class Map {

    private final MapSerivce mapSerivce;

    @GetMapping("stores")
    public List<StoreDto> getStores() {
        return mapSerivce.findAll();
    }

    @GetMapping("/store/load")
    public String loadStore() {
        mapSerivce.insertStoreList("data/all_starbucks_store_list.csv");
        return "Store list DB 저장 완료";
    }
    
}
