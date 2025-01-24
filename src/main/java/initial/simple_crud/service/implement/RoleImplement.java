package initial.simple_crud.service.implement;

import initial.simple_crud.dto.request.RoleRequestDTO;
import initial.simple_crud.dto.response.RoleResponseDTO;

import java.util.List;

public interface RoleImplement {
    List<RoleResponseDTO> getAll();
    RoleResponseDTO create(RoleRequestDTO requestDTO);
    RoleResponseDTO findById(Long roleId);
    RoleResponseDTO updateById(Long roleId, RoleRequestDTO requestDTO);
    void delete(Long roleId);
}
