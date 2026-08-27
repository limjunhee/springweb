package example.day02_2.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import example.day02_2.model.dao.WaitingDao;
import example.day02_2.model.dto.WaitingDto;

@RestController
public class WaitingController {
    // 컨트롤러에서 dao 호출
    private WaitingDao wd = WaitingDao.getInstance();

    // [1] 대기명단 등록
    @PostMapping("/waiting/save")
    public boolean save(WaitingDto waitingDto){
        boolean result = wd.save(waitingDto);
        return result;
    }

    // [2] 대기명단 전체조회
    @GetMapping("waiting/findAll")
    public ArrayList<WaitingDto> findAll(){
        ArrayList<WaitingDto> result = wd.findAll();

        return result;
    }

    // [3] 대기명단 개별 수정
    @PutMapping("waiting/update")
    public boolean update(WaitingDto waitingDto){
        boolean result = wd.update(waitingDto);

        return result;
    }

    // [4] 대기 명단 개별 삭제
    @DeleteMapping("waiting/delete")
    public boolean delete(@RequestParam("phoneNumber")String phoneNumber){
        boolean result = wd.delete(phoneNumber);

        return result;
    }
}
