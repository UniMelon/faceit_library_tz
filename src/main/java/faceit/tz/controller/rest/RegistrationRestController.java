package faceit.tz.controller.rest;

import faceit.tz.controller.exception.UserAlreadyExistException;
import faceit.tz.model.dto.UserDto;
import faceit.tz.model.mapper.UserMapper;
import faceit.tz.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegistrationRestController {

    private final UserService userService;

    public RegistrationRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> userRegistration(@Valid @RequestBody UserDto userDto) throws MessagingException {
        try {
            userService.register(UserMapper.INSTANCE.toEntity(userDto));

        }catch (UserAlreadyExistException e){
            throw new UserAlreadyExistException("User already exist");
        }
        return new ResponseEntity<>("Check email for verify account", HttpStatus.CREATED);
    }

    @GetMapping("/{token}")
    public ResponseEntity<String> verifyCustomer(@PathVariable String token){
        userService.verifyUser(token);

        return new ResponseEntity<>("Account verified.", HttpStatus.OK);
    }
}
