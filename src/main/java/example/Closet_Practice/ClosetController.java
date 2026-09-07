package example.Closet_Practice;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClosetController {
    @Autowired private ClosetService closetService;

    // [1]의류 등록
    // http://127.0.0.1:8080/closet?mno=1
    // {"cno":1301,"clname":"테스트옷","clcolor":"blue"}
    @PostMapping("/closet")
    public boolean clothesAdd(@RequestBody ClothesDto closetDto, @RequestParam(name = "mno")int mno){
        return closetService.clothesAdd(closetDto, mno);
    }

    // [2] 의류 전체조회
    @GetMapping("/closet")
    public ArrayList<ClothesDto> clothesPrintAll(){
        return closetService.clothesPrintAll();
    }
}
