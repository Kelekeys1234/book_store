package oniline_bookstore_service.user.service;

import java.time.LocalDateTime;

import java.util.Optional;
import java.util.Random;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import oniline_bookstore_service.Dao.OTPDao;
import oniline_bookstore_service.Dao.UserDao;
import oniline_bookstore_service.user.model.OTP;
import oniline_bookstore_service.user.model.OTPTypeEnum;
import oniline_bookstore_service.user.model.User;

@Service
@Slf4j
public class OTPService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private OTPDao otpDao;

	private static final Random random = new Random();

	public OTP createOtp(User user) {
	    log.info("generate otp for user registration");
	    String generateOtp = generateOTP();
	    OTP otp = new OTP();
	    otp.setOtp(generateOtp);
	    otp.setType(OTPTypeEnum.EMAIL_VERIFY);
	    otp.setIsConsumed(false);
	    otp.setExpiryTime(LocalDateTime.now().plusMinutes(10));
	    otp.setCreatedAt(LocalDateTime.now());
	    otp.setUser(user);  // Ensure the user is set correctly on the OTP
	    return otp;
	}


	public void checkIfOtpIsVerified(String userName) throws BadRequestException {
		User user = userDao.findByUserName(userName)
				.orElseThrow(() -> new UsernameNotFoundException("user not found " + userName));
		Optional<OTP> otpOptional = user.getOtps().stream().filter(otp -> otp.getIsConsumed()).findFirst();
		if (otpOptional.isEmpty()) {
			log.info("No consumed OTP found for user {}.", user.getId().toString());
			throw new BadRequestException("user not verify.");
		}
	}

	public String generateOTP() {
		// Generate a random 6-digit OTP
		int otpNumber = 100000 + random.nextInt(900000);
		return String.valueOf(otpNumber);
	}

}
