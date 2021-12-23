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

    @Size(max = 32, message = "username must be up to 32 characters!")
    private String username;

    @CustomValidPassword
    private String password;

    private String role;

    @Email
    private String email;
}
