package com.basic.myboard.controller;

import com.basic.myboard.entity.Board;
import com.basic.myboard.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.csrf.CsrfToken;
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
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/boardList")
    public String boardList(Model model) {
        ArrayList<Board> boardList = (ArrayList<Board>) boardService.getAllBoards();
        boardList.forEach(obj -> logger.info("obj : {}", obj));
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

    @GetMapping("/delete")
    public String deleteOneBoard(int id) {
        logger.info("id : {}", id);
        return "redirect:/board/boardList";
    }

    @GetMapping("csrf-token")
    public String retrieveCsrfToken(HttpServletRequest request) {
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        logger.info("token : {}", token);
        return "redirect:/";
    }
}