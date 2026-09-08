package example.practice5.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.practice5.model.dto.BoardDto;
import example.practice5.model.dto.CommentDto;
import example.practice5.model.entity.BoardEntity;
import example.practice5.model.entity.CommentEntity;
import example.practice5.model.repository.BoardRepository;
import example.practice5.model.repository.CommentRepository;


@Service 
public class BoardService {
    @Autowired private BoardRepository boardRepository;
    @Autowired private CommentRepository commentRepository;
    
    // 게시물 추가
    public boolean addBoard( BoardDto boardDto) {
        BoardEntity boardEntity = boardDto.toEntity();

        BoardEntity savedBoardEntity = boardRepository.save(boardEntity);
        savedBoardEntity.setComments(null);

        if (savedBoardEntity.getId() >= 1) {
            return true;
        } else {
            return false;
        }
    }

    // 게시물 전체조회
    public List<BoardDto> boardPrint(){
        // 게시글 엔티티 모두 찾아옴
        List<BoardEntity> boardEntities = boardRepository.findAll();
        // 반환용 DTO 선언
        ArrayList<BoardDto> boardDtos = new ArrayList<>();

        // 게시글 엔티티 하나하나 꺼내서 보기
        for(BoardEntity entity : boardEntities){
            // 게시글 엔티티를 DTO로 변환
            BoardDto boardDto = BoardDto.from(entity);
            
            // 해당 게시글 엔티티를 참조하는 댓글 목록 엔티티를 DTO로 변환
            // 
            // Optional<CommentEntity> optional = commentRepository.findById(entity.getId());
            // if (optional.isPresent()) {
            //     CommentEntity commentEntity = optional.get();
            //     entity.getComments().forEach( (comment) -> {
            //         boardDto.getComments().add( CommentDto.from(comment) );
            //     });
            // }
            //
            entity.getComments().forEach( (comment) -> {
                boardDto.getComments().add( CommentDto.from(comment) );
            });

            boardDtos.add(boardDto);
        }

        return boardDtos;
    }

    public boolean boardDelete(Integer id, String password){

        Optional<BoardEntity> optional = boardRepository.findById(id);

        if (optional.isPresent() && optional.get().getPassword().equals(password)) {
            boardRepository.deleteById(id);

            return true;
        } else {
            return false;
        }
    }
}
