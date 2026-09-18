package example.totalpractice1.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import example.totalpractice1.model.entity.ProductEntity;


public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
