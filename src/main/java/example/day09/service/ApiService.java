package example.day09.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.day09.model.dto.ApiDto;
import example.day09.model.entity.ApiEntity;
import example.day09.model.repository.ApiRepository;

@Service
public class ApiService {
    @Autowired private ApiRepository apiRepository;

    public List<ApiDto> findAll(){

        List<ApiEntity> entities = apiRepository.findAll();


        // List<ApiDto> dtos = entities.stream().map((entity) -> {return ApiDto.from(entity);}).toList();
        List<ApiDto> dtos = new ArrayList<>();

        for(ApiEntity entity : entities){
            ApiDto dto = ApiDto.from(entity);

            dtos.add(dto);
        }
        
        return dtos;
    }

    public boolean save ( ApiDto apiDto ){
        // 1. dto -> entity
        ApiEntity entity = apiDto.toEntity();
        // 2. 리포지토리 save
        ApiEntity saved = apiRepository.save(entity);
        // 3. save 결과 판단
        if ( saved.getIdx() >= 1) {
            return true;
        }
        return false;
    }
}