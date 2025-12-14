package com.example.starbucks.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.starbucks.dao.MenuDao;
import com.example.starbucks.dto.MenuDto;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuDao menuDao;

    public List<MenuDto> getMenus(){
        return menuDao.findAllMenus();
    }

    public MenuDto findMenuById(int menuId){
        return menuDao.findMenuById(menuId);
    }

    public void insertFromCsv(String resourcePath) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new RuntimeException(resourcePath + " 파일을 찾을 수 없습니다.");
            }

            try (CSVReader csvReader = new CSVReaderBuilder(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                    .withSkipLines(1)  // 헤더 스킵
                    .build()) {

                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    MenuDto menu = new MenuDto();

                    menu.setCategory(line[0]);                       // 분류
                    menu.setVolume(line[1]);                         // 1회 제공량
                    menu.setMenuName(line[2]);                           // 메뉴명
                    menu.setKcal(parseIntSafe(line[3]));          // 칼로리(kcal)
                    menu.setSugars(parseIntSafe(line[4]));            // 당류(g)
                    menu.setProtein(parseIntSafe(line[5]));          // 단백질(g)
                    menu.setSodium(parseIntSafe(line[6]));           // 나트륨(mg)
                    menu.setSaturatedFat(parseFloatSafe(line[7]));   // 포화지방(g)
                    menu.setCaffeine(parseIntSafe(line[8]));         // 카페인(mg)

                    menuDao.insertMenu(menu);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int parseIntSafe(String str) {
        try {
            return Integer.parseInt(str.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    private float parseFloatSafe(String str) {
        try {
            return Float.parseFloat(str.trim());
        } catch (Exception e) {
            return 0.0f;
        }
    }
    
}
