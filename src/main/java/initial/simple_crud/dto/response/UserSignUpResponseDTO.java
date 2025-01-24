package initial.simple_crud.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSignUpResponseDTO {
    private Long id;
    private String fullname;
    private String email;
    private String password;
    private List<UserRoleResponseDTO> userRoleList;
}
