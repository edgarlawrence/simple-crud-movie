package initial.simple_crud.service;

import initial.simple_crud.dto.request.RoleRequestDTO;
import initial.simple_crud.dto.response.RoleResponseDTO;
import initial.simple_crud.model.Role;
import initial.simple_crud.repository.RoleRepository;
import initial.simple_crud.service.implement.RoleImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService implements RoleImplement {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<RoleResponseDTO> getAll() {
        List<Role> roles = roleRepository.findAll();

        return roles.stream().map(role -> RoleResponseDTO.builder()
                .id(role.getId())
                .name(role.getName()).build())
                .collect(Collectors.toList());
    }

    @Override
    public RoleResponseDTO create(RoleRequestDTO requestDTO) {
        Role role = new Role();
        role.setName(requestDTO.getRoleName());

        roleRepository.save(role);

        return RoleResponseDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    @Override
    public RoleResponseDTO findById(Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role Was Not Found"));

        return RoleResponseDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    @Override
    public RoleResponseDTO updateById(Long roleId, RoleRequestDTO requestDTO) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role Was Not Found"));

        role.setName(requestDTO.getRoleName());

        roleRepository.save(role);

        return RoleResponseDTO.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    @Override
    public void delete(Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Role Was Not Found"));

        roleRepository.delete(role);
    }
}
