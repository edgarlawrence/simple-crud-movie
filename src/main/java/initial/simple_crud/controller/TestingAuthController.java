package initial.simple_crud.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController
public class TestingAuthController {

    @GetMapping(path = "/super")
    public ResponseEntity<String> superAdminTest() {
        return ResponseEntity.ok("This is Super Admin");
    }
}
