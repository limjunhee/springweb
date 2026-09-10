package example.practice5_test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.practice5_test.model.dto.CommentDto;
import example.practice5_test.model.entity.BoardEntity;
import example.practice5_test.model.entity.CommentEntity;
import example.practice5_test.model.repository.BoardRepository;
import example.practice5_test.model.repository.CommentRepository;

@Service 
public class CommentService {
    @Autowired private CommentRepository commentRepository;
    @Autowired private BoardRepository boardRepository;

    // [1] 댓글 등록
    public boolean commentAdd( CommentDto commentDto ){
        // 1. 입력된 댓글 dto -> 엔티티 변환
        CommentEntity commentEntity = commentDto.toEntity();

        // 2. 게시물 엔티티 불러오기 commentDto의 FK(게시물번호) 기준으로
        BoardEntity boardEntity = boardRepository.findById(commentDto.getBoardId()).orElse(null);

        // 3. 댓글 entity에 게시글 엔티티 넣기
        commentEntity.setBoardEntity(boardEntity);

        // 4. 저장하기
        CommentEntity savedCommentEntity = commentRepository.save(commentEntity);

        // 5. 결과 확인
        if (savedCommentEntity.getId() >= 1) {
            return true;
        }

        return false;
    }

    // [2] 댓글 삭제
    public boolean commentDelete(Integer commentId, String password){
        // 1. 삭제할 댓글 엔티티 commentId로 탐색
        CommentEntity commentEntity = commentRepository.findById(commentId).orElse(null);

        // 2. 댓글 엔티티 존재하고, 입력 패스워드가 올바르다면? -> 삭제
        if ( commentEntity.getId() >= 1 ) {
            if (commentEntity.getPassword().equals(password)) {
                commentRepository.deleteById(commentId);
                return true;
            }
        }

        return false;
    }
}
