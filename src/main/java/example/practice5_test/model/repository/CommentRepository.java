package example.practice5_test.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import example.practice5_test.model.entity.CommentEntity;

@Repository 
public interface CommentRepository extends JpaRepository<CommentEntity,Integer>{

}
