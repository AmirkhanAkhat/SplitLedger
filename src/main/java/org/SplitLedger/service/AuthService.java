package org.SplitLedger.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.SplitLedger.config.JwtUtil;
import org.SplitLedger.dto.LoginDTO;
import org.SplitLedger.dto.LoginResponse;
import org.SplitLedger.dto.RegisterDTO;
import org.SplitLedger.dto.RegisterResponse;
import org.SplitLedger.entity.User;
import org.SplitLedger.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public RegisterResponse registerUser(RegisterDTO registerDTO) {
        if(userRepository.existsByEmail(registerDTO.getEmail())){
            log.info("There is already existing user with such {} email.", registerDTO.getEmail());
            throw new IllegalArgumentException("Email already registered");
        }

        User newUser = new User();
        newUser.setEmail(registerDTO.getEmail());
        newUser.setUsername(registerDTO.getUsername());
        newUser.setPassword(encoder.encode(registerDTO.getPassword()));

        userRepository.save(newUser);

        return new RegisterResponse(newUser.getId(), registerDTO.getUsername(), registerDTO.getEmail(), newUser.getCreatedAt());
    }


    public LoginResponse login(LoginDTO loginDTO) {
        if (!userRepository.existsByEmail(loginDTO.getEmail())) {
            log.info("There is no existing user with such {} email. Please sign up for new account or log in with existing one.", loginDTO.getEmail());
            throw new IllegalArgumentException("No such user");
        }

        User user = userRepository.findByEmail(loginDTO.getEmail());

        if(!encoder.matches(loginDTO.getPassword(), user.getPassword())) {
            log.info("Incorrect password. Retype again!");
            throw new IllegalArgumentException("Incorrect password.");
        }

        String jwtToken = jwtUtil.generateToken(user.getUsername());

        return new LoginResponse(jwtToken, user.getId(), user.getUsername(), loginDTO.getEmail());
    }

}



