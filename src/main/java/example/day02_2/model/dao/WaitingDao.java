package example.day02_2.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import example.day02_2.model.dto.WaitingDto;

public class WaitingDao extends BaseDao{

    //dao 싱글톤
    private WaitingDao(){};
    private static final WaitingDao instance = new WaitingDao();
    public static WaitingDao getInstance(){return instance;}

    // [1] 대기명단 등록
    public boolean save(WaitingDto waitingDto){
        try {
            // 1. SQL 작성
            String sql = "insert into waiting(phonenumber, count) values(? ,?)";

            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);

            // 3. 매개변수 대입
            ps.setString(1, waitingDto.getPhoneNumber());
            ps.setInt(2, waitingDto.getCount());
            // 4. 실행 -> 반영된 레코드 수 가져오기
            int result = ps.executeUpdate();

            // 5. 결과 반환
            if (result == 1) {
                return true;
            }
             
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    // [2] 대기명단 전체 조회
    public ArrayList<WaitingDto> findAll(){
        // 반환할 결과를 담을 리스트
        ArrayList<WaitingDto> result = new ArrayList<>();

        try {
            // 1. sql 작성
            String sql = "select * from waiting";

            // 2. sql 기재
            PreparedStatement ps = conn.prepareStatement(sql);

            // 3. sql 실행
            ResultSet rs = ps.executeQuery();

            // 4. 실행문 결과리스트에 담기(레코드 DTO로 변환하고 결과리스트에 담기)
            while (rs.next()) {
                WaitingDto waitingDto = new WaitingDto();

                waitingDto.setNo(rs.getInt("no"));
                waitingDto.setPhoneNumber(rs.getString("phonenumber"));
                waitingDto.setCount(rs.getInt("count"));
            
                result.add(waitingDto);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return result;
    }

    // [3] 대기명단 개별 수정
    public boolean update(WaitingDto waitingDto){
        try {
            // 1. SQL 작성
            String sql = "update waiting set count = ? where phonenumber = ?";

            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);

            // 3. 매개변수 와일드카드에 대입
            ps.setInt(1, waitingDto.getCount());
            ps.setString(2, waitingDto.getPhoneNumber());

            // 4. SQL 실행하기
            int result = ps.executeUpdate();

            // 5. 결과 확인하기
            if (result == 1) { // 등록된 레코드 수가 1개라면 성공
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    // [4] 대기명단 개별 삭제
    public boolean delete(String phoneNumber){
        try {
            // 1. SQL 작성
            String sql = "delete from waiting where phonenumber = ?";

            // 2. SQL 기재
            PreparedStatement ps = conn.prepareStatement(sql);

            // 3. 매개변수 대입
            ps.setString(1, phoneNumber);

            // 4. SQL 실행
            int result = ps.executeUpdate();

            // 5. 결과 확인(영향받은 레코드 수가 1인가?)
            if (result == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
    }
}
