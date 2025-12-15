package com.example.starbucks.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDto {
    private int id;                 // 주문 ID (PK)
    private int storeId;            // 매장 ID
    private String orderType;        // TOGO / STORE
    private int totalPrice;          // 총 결제 금액
    private String orderStatus;      // ORDERED / COMPLETE
    private LocalDateTime orderTime; // 주문 시간
}
