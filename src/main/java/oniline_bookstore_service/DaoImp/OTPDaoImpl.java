package oniline_bookstore_service.DaoImp;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import oniline_bookstore_service.Dao.OTPDao;
import oniline_bookstore_service.repository.OTPRepository;
import oniline_bookstore_service.user.model.OTP;

@Service
@Slf4j
public class OTPDaoImpl implements OTPDao{
	@Autowired
	private OTPRepository otpRepository;

	@Override
	public OTP createUpdateOtp(OTP otp) {
		log.info("saving otp inside database");
		return otpRepository.save(otp);
	}

	@Override
	public OTP getByOtp(String otp) {
		// find by otp ....
		return otpRepository.findByOtp(otp);
	}

}
