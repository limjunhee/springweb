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

    // [1] 게시글 등록 기능
    @PostMapping ("")
    public boolean addBoard( @RequestBody BoardDto boardDto ){
        return boardService.addBoard(boardDto);
    }

    // [2] 게시글 목록 조회 기능
    @GetMapping ("")
    public List<BoardDto> printBoard(){
        return boardService.printBoard();
    }

    // [3] 게시글 삭제 기능
    @DeleteMapping ("")
    public boolean deleteBoard(@RequestParam (name = "id")Integer id, @RequestParam (name="password")String password){
        return boardService.deleteBoard(id, password);
    }
}
