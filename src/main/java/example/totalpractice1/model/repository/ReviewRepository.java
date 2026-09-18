package example.totalpractice1.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import example.totalpractice1.model.entity.ReviewEntity;


public interface ReviewRepository extends JpaRepository<ReviewEntity, Integer> {
}
