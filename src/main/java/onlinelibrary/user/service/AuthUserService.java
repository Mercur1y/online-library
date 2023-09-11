package onlinelibrary.user.service;

import lombok.RequiredArgsConstructor;
import onlinelibrary.common.handling.exception.ResourceNotFoundException;
import onlinelibrary.common.handling.exception.UserExistsException;
import onlinelibrary.common.payload.request.LoginRequest;
import onlinelibrary.common.payload.request.SignupRequest;
import onlinelibrary.cons.UserRoles;
import onlinelibrary.user.domain.Customer;
import onlinelibrary.user.domain.Role;
import onlinelibrary.user.domain.User;
import onlinelibrary.user.repo.RoleRepository;
import onlinelibrary.user.repo.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthUserService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;
    private final UserRepository repository;

    public String getToken(LoginRequest loginRequest) {
        User user = repository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с логином " + loginRequest.getUsername() + " не найден"));

        if (!encoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new ResourceNotFoundException("Неверный пароль");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtService.generateJwtToken(authentication);
    }

    public void addUser(SignupRequest signUpRequest) {
        if (repository.existsByUsername(signUpRequest.getUsername())) {
            throw new UserExistsException("Пользователь с таким логином уже зарегистрирован");
        }

        if (repository.existsByEmail(signUpRequest.getEmail())) {
            throw new UserExistsException("E-mail уже зарегистрирован!");
        }

        Customer user = new Customer();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getName());
        user.setLastName(signUpRequest.getLastName());
        user.setActive(1);
        Role userRole = roleRepository.findByName(UserRoles.Fields.CUSTOMER)
                .orElseThrow(() -> new RuntimeException("Ошибка присвоения роли."));
        user.setRoles(Collections.singleton(userRole));
        repository.save(user);
    }

}
