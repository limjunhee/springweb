package example.practice5.service;

import example.practice5.model.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


import example.practice5.model.dto.CommentDto;
import example.practice5.model.entity.BoardEntity;
import example.practice5.model.entity.CommentEntity;
import example.practice5.model.repository.CommentRepository;

@Service
public class CommentService {
    @Autowired private CommentRepository commentRepository;
    @Autowired private BoardRepository boardRepository;

    public boolean addComments(CommentDto commentDto){
        // 입력받은 DTO -> Entity로 바꾸기
        CommentEntity commentEntity = commentDto.toEntity();

        // 게시물 FK값 찾아서 엔티티로 뽑아오기 -> 댓글 엔티티의 게시물 엔티티 참조 부분에 할당
        BoardEntity boardEntity = boardRepository.findById(commentDto.getBoardId() ).orElse(null);
        commentEntity.setBoardEntity((boardEntity));

        // 저장하기(리포지토리)
        CommentEntity savedCommentEntity = commentRepository.save(commentEntity);

        // 결과 확인
        if (savedCommentEntity.getId() >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteComments (Integer commentId, String password){
        // 파라미터 ID값으로 엔티티 하나 불러오기
        CommentEntity commentEntity = commentRepository.findById(commentId).orElse(null);

        // 엔티티가 있으면? & 입력 pw == 엔티티 pw이면? -> 삭제
        if (commentEntity != null) {
            if (commentEntity.getPassword().equals(password)) {
                commentRepository.deleteById(commentId);
                return true;
            }
        }
        return false;
    }
}
