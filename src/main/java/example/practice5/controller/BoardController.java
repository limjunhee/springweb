package example.practice5.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.practice5.model.dto.BoardDto;
import example.practice5.service.BoardService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping ("/api/board")
public class BoardController {

    @Autowired private BoardService boardService;

    // 게시글 등록 기능
    @PostMapping("")
    public boolean addBoard(@RequestBody BoardDto boardDto) {
        return boardService.addBoard(boardDto);
    }
    
    // 게시글 전체 조회
    @GetMapping("")
    public List<BoardDto> boardPrint(){
        return boardService.boardPrint();
    }

    // 게시글 삭제 기능
    @DeleteMapping("")
    public boolean boardDelete( @RequestParam (name = "id") Integer id, @RequestParam (name = "password") String password ){
        return boardService.boardDelete(id, password);
    }
}