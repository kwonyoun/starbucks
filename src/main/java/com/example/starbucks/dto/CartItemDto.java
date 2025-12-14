package com.example.starbucks.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemDto {
    private int storeId;
    private String storeName;

    private int menuId;
    private String menuName;
    // private int price;

    private String orderType; // TOGO / STORE
    private int quantity;

}
