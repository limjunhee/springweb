package example.day02.model.dao;

import java.sql.PreparedStatement;

import example.day02.model.dto.BoardDto;

public class BoardDao extends BaseDao {
    private BoardDao(){}
    private static final BoardDao instance = new BoardDao();
    public static BoardDao getInstance(){ return instance; }

    public boolean save( BoardDto boardDto ){
        try {
            // 1. sql 작성
            String sql = "insert into board(cintent,writer) values( ? , ? )";
            
            // 2. sql 기재 (외부 SQL 서버에 전달) -> sql은 자바가 아니므로 간접적인 인터페이스를 통해 전달
            PreparedStatement ps = conn.prepareStatement(sql);

            // 3. 기재된 sql에 매개변수 대입(ps.set() -> 1번 물음표에 content를, 2번에 writer 대입)
            ps.setString( 1, boardDto.getContent() );
            ps.setString( 2, boardDto.getWriter() );
            
            // 4. 기재된 sql 실행
            // ps.execute() -> 단순 실행 / ps.executeQuery(); -> 실행 후 반영된 레코드 수를 반환 
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
}
