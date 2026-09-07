package example.practice4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.practice4.model.dto.EnrollDto;
import example.practice4.service.EnrollService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController 
@RequestMapping ("api/enroll")
public class EnrollController {
    @Autowired private EnrollService enrollService;

    @PostMapping("")
    public boolean enrollAdd(@RequestBody EnrollDto enrollDto){
        return enrollService.enrollAdd(enrollDto);
    }

    @GetMapping("/detail")
    public EnrollDto enrollPrint( 
        @RequestParam( name = "enrollId") Integer enrollId){
        return enrollService.enrollPrint(enrollId);
    }
    
}
