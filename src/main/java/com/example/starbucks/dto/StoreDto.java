package com.example.starbucks.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StoreDto {
    private int id;
    private String storeName;
    private float x;
    private float y;
    private String storeType;
    private String storeAddr;
    
}

