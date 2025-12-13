package com.example.starbucks.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuDto {

    private int id;
    private String category;         // category
    private String volume;           // volume
    private String menuName;         // menu_name
    private int kcal;                // kcal
    private int sugars;              // sugars
    private int protein;             // protein
    private int sodium;              // sodium
    private float saturatedFat;      // saturated_fat
    private int caffeine;            // caffeine
    
}
