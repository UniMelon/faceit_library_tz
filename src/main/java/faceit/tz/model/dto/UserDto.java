package faceit.tz.model.dto;

import faceit.tz.annotation.CustomValidPassword;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class UserDto {
    private Long id;

    @Size(min = 5, max = 32, message = "{validation.size.username}")
    private String username;

    @CustomValidPassword(max = 32)
    private String password;

    private String role;

    @Email
    private String email;
}
