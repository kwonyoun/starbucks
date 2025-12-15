package com.example.starbucks.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.starbucks.dto.OrderDto;
import com.example.starbucks.dto.StoreDto;

@Mapper
public interface OrderDao {
    List<StoreDto> findNearestStores(double lat, double lng);
    StoreDto getStoreById(int id);
    void insertOrder(OrderDto order);
}
