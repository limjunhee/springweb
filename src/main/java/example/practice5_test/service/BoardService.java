package example.practice5_test.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.practice5_test.model.dto.BoardDto;
import example.practice5_test.model.dto.CommentDto;
import example.practice5_test.model.entity.BoardEntity;
import example.practice5_test.model.entity.CommentEntity;
import example.practice5_test.model.repository.BoardRepository;
import example.practice5_test.model.repository.CommentRepository;

@Service 
public class BoardService {

    @Autowired private BoardRepository boardRepository;
    @Autowired private CommentRepository commentRepository;

    // [1] 게시글 등록 기능
    public boolean addBoard(BoardDto boardDto){
        // 1. 등록된 dto -> entitiy화
        BoardEntity boardEntity = boardDto.toEntity();
        // 2. 방금 등록된 게시글 -> 댓글없음 == null
        boardEntity.setComments(null);

        // 3. 저장하기
        BoardEntity savedBoardEntity = boardRepository.save(boardEntity);

        // 4. 저장 검사하기
        if (savedBoardEntity.getId() >= 1) {
            return true;
        }

        return false;
    }

    // [2] 게시글 목록 조회 기능
    public List<BoardDto> printBoard(){
        // 1. 모든 게시글 엔티티 불러오기 findAll()
        List<BoardEntity> boardEntities = boardRepository.findAll();

        // 2. 반환값 담을 용도의 dto 리스트 불러오기
        List<BoardDto> boardDtos = new ArrayList<>();

        // 3. for -> 게시글 하나하나 꺼내서, dto화한 후 / 댓글 엔티티도 꺼내서 dto화한후 게시글 dto에 넣는다
        for(BoardEntity entity : boardEntities){
            BoardDto dto = BoardDto.from(entity);

            entity.getComments().forEach((comment) -> { dto.getComments().add(CommentDto.from(comment)); });

            boardDtos.add(dto);
        }

        // 4. dto 리스트 반환
        return boardDtos;
    }

    // [3] 게시글 삭제 기능
    public boolean deleteBoard(Integer id, String password){
        // 1. 엔티티 찾기
        BoardEntity boardEntity = boardRepository.findById(id).orElse(null);

        // 2. pk 값 검사, 비밀번호 일치 검사 후 삭제
        if (boardEntity.getId() >= 1) {
            if (boardEntity.getPassword().equals(password)) {
                boardRepository.deleteById(id);
                return true;
            }
        }
        // * 아님 말고
        return false;
    }
}
