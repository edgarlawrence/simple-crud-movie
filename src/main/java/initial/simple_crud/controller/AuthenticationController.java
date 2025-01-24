package initial.simple_crud.controller;

import initial.simple_crud.dto.request.UserLoginRequestDTO;
import initial.simple_crud.dto.request.UserRegisterRequestDTO;
import initial.simple_crud.dto.response.UserLoginResponseDTO;
import initial.simple_crud.dto.response.UserSignUpResponseDTO;
import initial.simple_crud.model.User;
import initial.simple_crud.service.AuthenticationService;
import initial.simple_crud.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<UserSignUpResponseDTO> register(@RequestBody UserRegisterRequestDTO registerUserDto) {
        var registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDTO> authenticate(@RequestBody UserLoginRequestDTO loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        UserLoginResponseDTO loginResponse = new UserLoginResponseDTO();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
