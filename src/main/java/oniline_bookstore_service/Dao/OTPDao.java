package oniline_bookstore_service.Dao;

import java.util.List;

import oniline_bookstore_service.user.model.OTP;

public interface OTPDao {

	OTP createUpdateOtp(OTP otp);

	OTP getByOtp(String otp);

}
