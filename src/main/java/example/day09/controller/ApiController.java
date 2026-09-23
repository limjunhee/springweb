package example.day09.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.day09.model.dto.ApiDto;
import example.day09.service.ApiService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping ("/api")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class ApiController {
    @Autowired private ApiService apiService;

    @GetMapping("")
    public List<ApiDto> findAll() {
        return apiService.findAll();
    }
    
    @PostMapping("")
    public boolean save( @RequestBody ApiDto apiDto) {
        return apiService.save(apiDto);
    }
}