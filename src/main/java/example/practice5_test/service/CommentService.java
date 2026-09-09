package example.practice5_test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.practice5_test.model.entity.BoardEntity;
import example.practice5_test.model.dto.CommentDto;
import example.practice5_test.model.entity.CommentEntity;
import example.practice5_test.model.repository.BoardRepository;
import example.practice5_test.model.repository.CommentRepository;

@Service 
public class CommentService {
    @Autowired private CommentRepository commentRepository;
    @Autowired private BoardRepository boardRepository;

    // 댓글 등록
    public boolean addComments(CommentDto commentDto){
        // dto를 엔티티로
        CommentEntity entity = commentDto.toEntity();

        // 엔티티에 게시물 ID(FK) 넣기
        BoardEntity boardEntity = boardRepository.findById(commentDto.getBoardId()).orElse(null);
        entity.setBoardEntity(boardEntity);

        // 저장
        CommentEntity savedCommentEntity = commentRepository.save(entity);

        // 결과 확인(댓글 엔티티 id여부로)
        if (savedCommentEntity.getId() >= 1) {
            return true;
        } else {
            return false;
        }
    }

    // 댓글 삭제
    public boolean deleteComments(Integer commentId, String password){
        // commentId 통해서 삭제할 댓글 엔티티 찾기
        CommentEntity entity = commentRepository.findById(commentId).orElse(null);

        // 만약 찾은 엔티티가 존재하고(댓글PK 1 이상), 패스워드가 일치한다면? -> 삭제
        if (entity.getId() >= 1) {
            if (entity.getPassword().equals(password)) {
                commentRepository.deleteById(commentId);
                return true;
            }
        }
        return false;
    }
}
