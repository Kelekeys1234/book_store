package oniline_bookstore_service.user.controller;

import org.apache.coyote.BadRequestException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import oniline_bookstore_service.dto.SignInRequest;
import oniline_bookstore_service.dto.SignUpRequest;
import oniline_bookstore_service.dto.TokenResponse;
import oniline_bookstore_service.handler.GenericResponse;
import oniline_bookstore_service.user.service.UserService;
import oniline_bookstore_service.dto.VerifyContact;

@RestController
@RequestMapping("/api/v1/user")
@Slf4j
public class UserController {

	@Autowired
	private UserService userService;

	 @PostMapping("/signUp")
	    public ResponseEntity<String> signUp(@RequestBody SignUpRequest signUpRequest) {
	        try {
	            log.info("Signing up user");
	            String token = userService.signUp(signUpRequest);
	            return ResponseEntity.ok()
	                    .body("User registration successful. Please copy your token and verify." + token);
	        } catch (BadRequestException e) {
	            log.error("Error while signing up user: {}", e.getMessage());
	            return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
	        } catch (Exception e) {
	            log.error("An unexpected error occurred while signing up user", e);
	            return buildErrorResponse("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    @PostMapping("/login")
	    public ResponseEntity<TokenResponse> signIn(@RequestBody SignInRequest signInRequest) {
	        
	            log.info("Signing in user");
	            TokenResponse tokenResponse = userService.signIn(signInRequest);
	            return ResponseEntity.ok()
	                    .body(tokenResponse);
	      
	    }

	    @PutMapping("/verify_contact")
	    public ResponseEntity<String> verifyContact(@RequestBody VerifyContact verifyContact) {
	        try {
	            log.info("Verifying user contact");
	             userService.verifyOtp(verifyContact);
	            return ResponseEntity.ok()
	                    .body("User OTP verification successful.");
	        } catch (BadRequestException e) {
	            log.error("Error while verifying user contact: {}", e.getMessage());
	            return buildErrorResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
	        } catch (Exception e) {
				log.error("An unexpected error occurred while verifying user contact", e);
	            return buildErrorResponse("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

	    private ResponseEntity<String> buildErrorResponse(String message, HttpStatus status) {
	        return ResponseEntity.status(status)
	                .body(message);
	    }
	}