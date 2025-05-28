package org.SplitLedger.controller;

import lombok.RequiredArgsConstructor;
import org.SplitLedger.dto.LoginDTO;
import org.SplitLedger.dto.LoginResponse;
import org.SplitLedger.dto.RegisterDTO;
import org.SplitLedger.dto.RegisterResponse;
import org.SplitLedger.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterDTO registerDTO){
        RegisterResponse registerResponse = authService.registerUser(registerDTO);
        return ResponseEntity.ok(registerResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDTO) {
        LoginResponse loginResponse = authService.login(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }


}
