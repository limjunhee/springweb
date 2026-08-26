package example.day02.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import example.day02.model.dto.BoardDto;

public class BoardDao extends BaseDao {
    private BoardDao(){}
    private static final BoardDao instance = new BoardDao();
    public static BoardDao getInstance(){ return instance; }

    // [1] 등록
    public boolean save( BoardDto boardDto ){
        try {
            // 1. sql 작성
            String sql = "insert into board(content,writer) values( ? , ? )";
            
            // 2. sql 기재 (외부 SQL 서버에 전달) -> sql은 자바가 아니므로 간접적인 인터페이스를 통해 전달
            PreparedStatement ps = conn.prepareStatement(sql);

            // 3. 기재된 sql에 매개변수 대입(ps.set() -> 1번 물음표에 content를, 2번에 writer 대입)
            ps.setString( 1, boardDto.getContent() );
            ps.setString( 2, boardDto.getWriter() );
            
            // 4. 기재된 sql 실행
            // ps.execute() -> 단순 실행 / ps.executeUpdate(); -> 실행 후 반영된 레코드 수를 반환 
            int result = ps.executeUpdate();
            
            // 5. sql 실행 결과
            if (result == 1) { // 등록된 레코드 수가 1개라면 성공
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    // [2] 전체 조회
    public ArrayList<BoardDto> findAll(){
        ArrayList<BoardDto> list = new ArrayList<>(); // 레코드 1개당 dto 한개, DTO 여러개면 배열 혹은 리스트
        try {
            // 1. SQL 작성
            String sql = "select * from board";

            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);

            // 3. 기재된 SQL 매개변수 대입(생략 -> 와일드카드 없음)

            // 4. SQL 실행
            ResultSet rs = ps.executeQuery();

            // 5. SQL 결과 처리
            while (rs.next()) {
                BoardDto dto = new BoardDto();
                dto.setNo(rs.getInt("no"));
                dto.setContent(rs.getString("content"));
                dto.setWriter(rs.getString("writer"));
                list.add(dto);
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return list;
    }

     
 	
    // [3] 개별수정 DAO
    public boolean update( BoardDto boardDto ){
        try{
            String sql = "update board set content = ? where no = ? ";// 1.1 SQL 작성
            
            PreparedStatement ps = conn.prepareStatement(sql); // 1.2 SQL 기재 *예외*
            
            ps.setString( 1 , boardDto.getContent() );// 1.3 SQL내 ? 매개변수대입
            ps.setInt( 2 , boardDto.getNo() );
            
            int result = ps.executeUpdate(); // 1.4 SQL 실행
            
            if( result == 1 ) return true; // 1.5 실행 결과 반환
        }catch( SQLException e ){ 
            System.out.println( e ); 
        }
        return false; // 1.5 실행 결과 반환
    }

     
 	
    // [4] 개별삭제 DAO
    public boolean delete(int no) {
        try {
            String sql = "delete from board where no = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, no); // SQL 문법내 첫번째 ? 에 매개변수 값 대입
            int result = ps.executeUpdate();
            if (result == 1)
                return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
}
