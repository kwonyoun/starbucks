package com.example.starbucks.service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.starbucks.dao.MapDao;
import com.example.starbucks.dto.StoreDto;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MapSerivce {

    private final MapDao mapDao;

    public List<StoreDto> findAll() {
        return mapDao.findAll();
    }

    public void insertStoreList(String resourcePath) {
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
                    StoreDto store = new StoreDto();

                    store.setStoreName(line[0]);
                    store.setY(parseFloatSafe(line[1]));
                    store.setX(parseFloatSafe(line[2]));
                    store.setStoreType(line[3]);
                    store.setStoreAddr(line[4]);       

                    mapDao.insertStoreList(store);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
