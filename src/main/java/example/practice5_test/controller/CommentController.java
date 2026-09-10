package example.practice5_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.practice5_test.model.dto.CommentDto;
import example.practice5_test.service.CommentService;

@RestController 
@RequestMapping ("/api/board/comments")
public class CommentController {
    @Autowired private CommentService commentService;

    // [1] 댓글 등록
    @PostMapping ("")
    public boolean commentAdd(@RequestBody CommentDto commentDto){
        return commentService.commentAdd(commentDto);
    }

    // [2] 댓글 삭제
    @DeleteMapping ("")
    public boolean commentDelete(@RequestParam (name="commentId")Integer commentId, @RequestParam (name="password")String password){
        return commentService.commentDelete(commentId, password);
    }
}
