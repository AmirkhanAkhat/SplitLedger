package org.SplitLedger.service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.SplitLedger.dto.RegisterDTO;
import org.SplitLedger.entity.User;
import org.SplitLedger.repository.UserRepository;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
@Slf4j
public class AuthService {

    private final UserRepository userRepository;

    public User registerUser(RegisterDTO registerDTO) {
        if(userRepository.existsByEmail(registerDTO.getEmail())){
            log.info("There is alreade existing user with such " + registerDTO.getEmail() + " email.");
        }

        User newUser = new User();
        newUser.setEmail(registerDTO.getEmail());
        newUser.setUsername(registerDTO.getUsername());
        newUser.setPassword(registerDTO.getPassword());
        newUser.setPhoneNumber(registerDTO.getPhoneNumber());

        userRepository.save(newUser);
        return newUser;
    }

}
