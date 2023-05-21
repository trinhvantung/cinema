package vn.trinhtung.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.trinhtung.entity.ResetPasswordToken;

import java.util.Optional;

@Repository
public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, String> {
    @Query("select r from ResetPasswordToken r where r.user.email = :email and r.resetPasswordToken = :token")
    Optional<ResetPasswordToken> findByEmailAndToken(String email, String token);
}
