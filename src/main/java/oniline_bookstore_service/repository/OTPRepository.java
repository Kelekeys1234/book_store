package oniline_bookstore_service.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import oniline_bookstore_service.user.model.OTP;

@Repository
public interface OTPRepository extends JpaRepository<OTP,UUID>{
	OTP findByOtp(String otp);

}
