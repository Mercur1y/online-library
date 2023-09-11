package onlinelibrary.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import onlinelibrary.common.payload.request.LoginRequest;
import onlinelibrary.common.payload.request.SignupRequest;
import onlinelibrary.common.payload.response.JwtResponse;
import onlinelibrary.common.payload.response.MessageResponse;
import onlinelibrary.user.service.AuthUserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthUserService userService;


    @PostMapping("/signin")
    public JwtResponse authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return new JwtResponse(userService.getToken(loginRequest));
    }

    @PostMapping("/signup")
    public MessageResponse registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        userService.addUser(signUpRequest);
        return new MessageResponse("Пользователь успешно зарегистрирован!");
    }
}
