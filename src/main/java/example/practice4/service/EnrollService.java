package example.practice4.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.practice4.model.repository.EnrollRepository;

@Service
public class EnrollService {
    @Autowired private EnrollRepository enrollRepository;
}
