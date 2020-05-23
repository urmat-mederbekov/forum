package kg.attractor.forum.service;

import kg.attractor.forum.dto.UserDTO;
import kg.attractor.forum.exception.CustomerAlreadyRegisteredException;
import kg.attractor.forum.model.CustomerRegisterForm;
import kg.attractor.forum.model.User;
import kg.attractor.forum.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder encoder;

    public void register(CustomerRegisterForm form) {
        if (userRepo.existsByEmail(form.getEmail())) {
            throw new CustomerAlreadyRegisteredException();
        }

        var user = User.builder()
                .email(form.getEmail())
                .username(form.getName())
                .password(encoder.encode(form.getPassword()))
                .build();

        userRepo.save(user);
    }

    public UserDTO getByEmail(String email) {

        var user = userRepo.findByEmail(email).get();
//                .orElseThrow(CustomerNotFoundException::new);

        return UserDTO.from(user);
    }
}
