package com.cripttion.chatapp.Controller;

import com.cripttion.chatapp.Dto.ApiResonseDto;
import com.cripttion.chatapp.model.entity.User;
import com.cripttion.chatapp.service.onboarding.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "/v1/api")
public class OnBoardingController {
    @Autowired
    private LoginService loginService;
    @GetMapping(path = "/login")
    public ResponseEntity<ApiResonseDto> login(@RequestParam("mobileNumber") String mobileNumber , @RequestParam("password") String password)
    {
        return new ResponseEntity<>(
                loginService.login(mobileNumber,password),
                HttpStatus.OK
    );
    }


}
