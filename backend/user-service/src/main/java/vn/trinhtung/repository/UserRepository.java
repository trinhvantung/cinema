package vn.trinhtung.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.trinhtung.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
	
	Optional<User> findByActivationCode(String activationCode);

	@Query("select new vn.trinhtung.entity.User(u.id, u.fullName) from User u where u.id in :ids")
	List<User> findAllById(List<Integer> ids);
}
