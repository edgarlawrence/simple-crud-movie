package initial.simple_crud.controller;

import initial.simple_crud.dto.request.RoleRequestDTO;
import initial.simple_crud.dto.response.RoleResponseDTO;
import initial.simple_crud.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/role")
@RestController
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> getAll() {
        var roles = roleService.getAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoleResponseDTO> create(@RequestBody RoleRequestDTO requestDTO) {
        var roles = roleService.create(requestDTO);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<RoleResponseDTO> findById(@PathVariable Long id) {
        var roles = roleService.findById(id);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<RoleResponseDTO> updateByid(@PathVariable Long id, @RequestBody RoleRequestDTO requestDTO) {
        var roles = roleService.updateById(id, requestDTO);
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return new ResponseEntity<>("Role Has been Deleted", HttpStatus.OK);
    }
}
