package initial.simple_crud.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRoleResponseDTO {
    private Long roleId;
    private String roleName;
    private Boolean isActive;
}
