package example.practice5_test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.practice5_test.model.dto.BoardDto;
import example.practice5_test.service.BoardService;

@RestController 
@RequestMapping ("/api/board")
public class BoardController {
    @Autowired private BoardService boardService;

    // 게시물 추가
    @PostMapping("")
    public boolean addBoard(@RequestBody BoardDto boardDto){
        return boardService.addBoard(boardDto);
    }

    // 게시물 전체조회
    @GetMapping("")
    public List<BoardDto> printBoard(){
        return boardService.printBoard();
    }
    // 게시물 삭제
    @DeleteMapping("")
    public boolean boardDelete(@RequestParam (name = "id")Integer commentId, @RequestParam(name="password")String password){
        return boardService.boardDelete(commentId,password);
    }
}
