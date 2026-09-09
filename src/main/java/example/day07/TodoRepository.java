package example.day07;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository 
public interface TodoRepository extends JpaRepository<TodoEntity,Integer>{
    // extends JpaRepository<조작할엔티티명, PK타입명>
    // 1. CRUD 메소드 제공: .save() .findAll() .findById( )
    //                      .deleteById() 등등
    
    // 2. 쿼리메소드: SQL 작성하지 않고 추상메소드 이름으로 쿼리 자동 생성 <- 카멜 표기법 사용
    // 반환타입 findBy필드명( 타입 매개변수명 );  주의) 필드명에 대소문자(카멜) 규칙을 지켜야함, 안지키면 실행도 안됨
    TodoEntity findByTitle( String title );
    List<TodoEntity> findByTitleAndContent( String title, String content );
    Map<String,Object> findByTitleAndOrContent( String title, String content);
    
    // 3. 네이티브쿼리 : SQL 직접 작성, 추상메소드 이름은 아무거나
    // 추상메소드 뒤에 @Query( value = "직접SQL작성", nativeQuery = true )
    // SQL 문법 내 매개변수 대입 시 ? 대신에 ":매개변수명"
    @Query (value = "select * from todo where title = :title ", nativeQuery = true)
    TodoEntity myQuery1( String title );

    @Query (value = "select * from todo where title = :title and content = :content", nativeQuery = true)
    List<TodoEntity> myquery2( String title, String content );

    @Query (value = "select * from todo where title = :title or content = :content", nativeQuery = true)
    List<TodoEntity> myquery3(String title, String content);

}
