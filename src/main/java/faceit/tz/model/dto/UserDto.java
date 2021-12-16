package faceit.tz.model.dto;

import faceit.tz.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;

    @Size(max = 32, message = "username must be up to 32 characters!")
    private String username;

    @Size(min = 8, max = 32, message = "username must be between 8 and 32 characters!")
    private String password;

    private String role;
}
