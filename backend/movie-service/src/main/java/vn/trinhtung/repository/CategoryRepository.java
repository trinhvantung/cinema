package vn.trinhtung.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.trinhtung.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
	Optional<Category> findByName(String name);

}
