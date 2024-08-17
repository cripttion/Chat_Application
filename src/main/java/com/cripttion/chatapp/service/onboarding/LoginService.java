package com.cripttion.chatapp.service.onboarding;

import com.cripttion.chatapp.Dto.ApiResonseDto;
import com.cripttion.chatapp.model.entity.User;
import com.cripttion.chatapp.repository.UserRepo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LoginService {

    @Autowired
    private UserRepo userRepo;

    @Data
    private static class LoginResponse {
        private String title;
        private UUID userId;
    }

    private User getUserByMobileNumber(String mobileNumber) {
        return userRepo.findByphoneNumber(mobileNumber)
                .orElse(null); // Handle case where user is not found
    }

    public ApiResonseDto<LoginResponse> login(String mobileNumber, String password) {
        ApiResonseDto<LoginResponse> apiResponseDto = new ApiResonseDto<>();
        mobileNumber ="+91"+mobileNumber;
        System.out.print(mobileNumber);
        User user = getUserByMobileNumber(mobileNumber);

        if (user != null && password.equals(user.getPassword())) {
            apiResponseDto.setStatus("200");
            apiResponseDto.setMessage("Login Successful");
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setTitle("User ID");
            loginResponse.setUserId(user.getUserId());
            apiResponseDto.setData(Collections.singletonList(loginResponse));
        } else {
            apiResponseDto.setStatus("500");
            apiResponseDto.setMessage("Unable to login. Invalid credentials.");
        }

        return apiResponseDto;
    }
}
