package com.example.starbucks.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.starbucks.dto.MenuDto;

@Mapper
public interface MenuDao {
    List<MenuDto> findAllMenus();
    MenuDto findMenuById(int menuId);
    void insertMenu(MenuDto menu);
    
}
