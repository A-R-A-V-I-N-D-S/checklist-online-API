package com.dailychecklist.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dailychecklist.entity.LoginDetails;
import com.dailychecklist.repository.LoginDetailsRepository;

@Service
public class LoginService {

	@Autowired
	private LoginDetailsRepository loginRepository;

	public String responseMessage = "";

	public boolean isUserExists(String userName) {
		boolean isExists = false;
		if (loginRepository.existsById(userName)) {
			isExists = true;
		}
		return isExists;
	}

	public LoginDetails saveNewUserDetails(String emailAddr, String userName, String password) {
		LoginDetails userDetails = new LoginDetails();
		if (!isUserExists(userName)) {
			userDetails.setUserName(userName);
			userDetails.setEmail(emailAddr);
			userDetails.setPassword(password);
			loginRepository.save(userDetails);
			responseMessage = "User successfully signed-up with the details";
		} else {
			responseMessage = "Username already exists";
		}
		return userDetails;
	}

	public void loginUser(String userName, String password) {
		if (!isUserExists(userName)) {
			responseMessage = "User does not exist. Please sign-up";
		} else {
			Optional<LoginDetails> userDetailsInDB = loginRepository.findById(userName);
			if (userDetailsInDB.get().getUserName().toString().equals(userName)
					&& userDetailsInDB.get().getPassword().toString().equals(password)) {
				responseMessage = "Successfully logged in";
			}else {
				responseMessage="Incorrect Password";
			}
		}
	}

}
