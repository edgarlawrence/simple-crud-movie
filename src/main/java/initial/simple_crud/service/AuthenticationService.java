package initial.simple_crud.service;

import initial.simple_crud.dto.request.UserLoginRequestDTO;
import initial.simple_crud.dto.request.UserRegisterRequestDTO;
import initial.simple_crud.dto.request.UserRoleRequestDTO;
import initial.simple_crud.dto.response.UserRoleResponseDTO;
import initial.simple_crud.dto.response.UserSignUpResponseDTO;
import initial.simple_crud.model.Role;
import initial.simple_crud.model.User;
import initial.simple_crud.model.UserRole;
import initial.simple_crud.repository.RoleRepository;
import initial.simple_crud.repository.UserRepository;
import initial.simple_crud.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RoleRepository roleRepository;

    public UserSignUpResponseDTO signup(UserRegisterRequestDTO input) {
        var user = new User();
        user.setFullname(input.getFullname());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));

        List<UserRole> userRoleList = new ArrayList<>();
        for (UserRoleRequestDTO requestDTO : input.getUserRoleRequestDTOList()) {
            if (input.getUserRoleRequestDTOList() == null || input.getUserRoleRequestDTOList().isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User roles must not be empty");
            }

            Role role = roleRepository.findById(requestDTO.getRoleId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role not found"));
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRole(role);
            userRole.setIsActive(true);
            userRoleList.add(userRole);
        }

        // Save the user first to ensure the User is persisted with an ID
        userRepository.save(user);

        // Save the UserRole relationships
        userRoleRepository.saveAll(userRoleList);

        // Attach the saved user roles to the user entity
        user.setUserRoleList(userRoleList);

        // Return UserSignUpResponseDTO with full details
        return UserSignUpResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullname(user.getFullname())
                .userRoleList(userRoleList.stream().map(userRole -> UserRoleResponseDTO.builder()
                        .roleId(userRole.getRole().getId()) // Get the Role ID from the Role entity
                        .roleName(userRole.getRole().getName()) // Get Role Description
                        .isActive(userRole.getIsActive()) // Get if the role is active
                        .build()).collect(Collectors.toList()))
                .build();
    }

    public User authenticate(UserLoginRequestDTO input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + input.getEmail()));
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}
