package example.practice5_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import example.practice5_test.model.dto.CommentDto;
import example.practice5_test.service.CommentService;

@RequestMapping ("/api/board/comments")
public class CommentController {
    @Autowired private CommentService commentService;

    // 댓글 등록
    @PostMapping("")
    public boolean addComments(@RequestBody CommentDto commentDto){
        return commentService.addComments(commentDto);
    }
    // 댓글 삭제
    @DeleteMapping("")
    public boolean deleteComments(@RequestParam(name = "id") Integer commentId, @RequestParam (name="password") String password){
        return commentService.deleteComments(commentId, password);
    }
}
