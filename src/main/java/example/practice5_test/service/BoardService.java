package example.practice5_test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import example.practice5_test.model.dto.BoardDto;
import example.practice5_test.model.dto.CommentDto;
import example.practice5_test.model.entity.BoardEntity;
import example.practice5_test.model.repository.BoardRepository;
import example.practice5_test.model.repository.CommentRepository;

@Service 
public class BoardService {
    @Autowired private BoardRepository boardRepository;
    @Autowired private CommentRepository commentRepository;

    // 게시물 추가
    public boolean addBoard(BoardDto boardDto){
        // dto -> entity
        BoardEntity entity = boardDto.toEntity();
        
        // entity 저장하기
        BoardEntity savedEntity = boardRepository.save(entity);
        
        // 처음 생성된 게시물은 댓글이 없으니 null 값 넣음
        savedEntity.getComments().add(null);

        if (savedEntity.getId() >= 1) {
            return true;
        } else {
            return false;
        }
        
    }
    // 게시물 전체조회
    public List<BoardDto> printBoard(){
        // 엔티티 다 뽑아오기
        List<BoardEntity> boardEntities = boardRepository.findAll();
        // 반환 dto 담을 list 만들기
        List<BoardDto> boardDtos = new ArrayList<>();
        // 엔티티 하나하나를 꺼내고, dto로 변환하기 / 댓글 엔티티 가져오고, dto로 변환해서 넣기
        for(BoardEntity entity : boardEntities){
            // 엔티티 하나 ->  dto 하나로 변환
            BoardDto dto = BoardDto.from(entity);

            // 게시물 엔티티에 연결된 댓글 가져와서 DTO로 변환시키고 -> 게시물 DTO에 add
            entity.getComments().forEach((commentEntity) 
                -> {dto.getComments().add(CommentDto.from(commentEntity));
            });

            boardDtos.add(dto);
        }

        return boardDtos;
    }


    // 게시물 삭제
    public boolean boardDelete(Integer boardId, String password){
        // commentId 파라미터 통해서 게시글 엔티티 하나 가져옴
        BoardEntity entity = boardRepository.findById(boardId).orElse(null);
        
        // 가져온 엔티티의 id가 1 이상이며, 입력한 비밀번호가 엔티티의 비번과 일치하다면?? -> 삭제
        if (entity.getId() >= 1) {
            if (entity.getPassword().equals(password)) {
                boardRepository.deleteById(boardId);    
                return true;
            }
        }
        return false;
    }
}
