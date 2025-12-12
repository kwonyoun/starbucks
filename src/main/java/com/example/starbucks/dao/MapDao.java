package com.example.starbucks.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.starbucks.dto.StoreDto;

@Mapper
public interface MapDao {
    List<StoreDto> findAll();   
    void insertStoreList(StoreDto store); 
}
