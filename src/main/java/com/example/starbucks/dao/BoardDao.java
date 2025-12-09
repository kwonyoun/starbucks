package com.example.starbucks.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.starbucks.dto.BoardDto;

@Mapper
public interface BoardDao {
    List<BoardDto> findAll();
    boolean save(BoardDto board);
    //선택 게시글 출력
    BoardDto find(int board);

    boolean update(int boardId, String boardTitle, String boardContent);

    boolean delete(int boardId);
}
