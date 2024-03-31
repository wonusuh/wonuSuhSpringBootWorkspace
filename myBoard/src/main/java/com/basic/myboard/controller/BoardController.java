package com.basic.myboard.controller;

import com.basic.myboard.entity.Board;
import com.basic.myboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/boardList")
    public String boardList(Model model) {
        ArrayList<Board> boardList = (ArrayList<Board>) boardService.getAllBoards();
        boardList.forEach((obj) -> {
            System.out.println(obj.toString());
        });
        model.addAttribute("boardList", boardList);
        return "/board/boardList";
    }

    @GetMapping("/boardForm")
    public String boardForm() {
        return "board/boardForm";
    }

    @PostMapping("/addBoard")
    public String addBoard(Board b) {

        boardService.saveBoard(b);
        return "/board/boardList";
    }
}
