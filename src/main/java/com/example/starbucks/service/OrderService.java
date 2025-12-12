package com.example.starbucks.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.starbucks.dao.OrderDao;
import com.example.starbucks.dto.StoreDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderDao orderDao;

    public List<StoreDto> getNearest(double lat, double lng) {
    return orderDao.findNearestStores(lat, lng);
}

    public StoreDto getStoreById(int id) {
        return orderDao.getStoreById(id);
    }

    
}
