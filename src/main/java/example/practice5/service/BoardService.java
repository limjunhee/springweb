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
        BoardEntity boardEntity = boardDto.toEntity(); // dto -> entity

        BoardEntity savedBoardEntity = boardRepository.save(boardEntity); // entity를 save
        savedBoardEntity.setComments(null); // 첫 댓글 목록은 null

        //pk 존재 시 성공
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
            
            // // 해당 게시글 엔티티를 참조하는 댓글 목록 엔티티를 DTO로 변환 (잘못된 버전)
            // // findById()로 또 찾아올 이유가 없음
            // // 같은 댓글들을 다 찾는 게 아니라 1개짜리 고정값을 반복해서 넣음
            // // boardDto.getComments().add( CommentDto.from(commentEntity)  // commentEntity가 아니라 comment를 가져오면 또 정상적임
            // // 하지만 이 경우에도 댓글 1번이 삭제되면 findById(1)이 비어서 if를 통과 못함 -> 그 다음 댓글도 이 게시물 소속이지만 하나도 안뜸
            // // 첫 댓글 PK와 게시물1의 PK가 우연히 1인 경우이기 때문에 맞아 보였을 뿐임
            // // PK와 게시물 번호가 어긋나면? -> 엉뚱한 게시물의 댓글이 딸려올 것으로 예상
            // Optional<CommentEntity> optional = commentRepository.findById(entity.getId());
            // if (optional.isPresent()) {
            //     CommentEntity commentEntity = optional.get();
            //     entity.getComments().forEach( (comment) -> {
            //         boardDto.getComments().add( CommentDto.from(commentEntity) );
            //     });
            // }
            
            // 해당 게시글 엔티티를 참조하는 댓글 목록 엔티티를 DTO로 변환
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
