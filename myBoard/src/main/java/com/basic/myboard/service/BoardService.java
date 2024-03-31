package com.basic.myboard.service;

import com.basic.myboard.entity.Board;
import com.basic.myboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    @Transactional
    public void saveBoard(Board b) {
        b.setBoardRegDate(String.valueOf(LocalDate.now()));
        boardRepository.save(b);
    }
}
