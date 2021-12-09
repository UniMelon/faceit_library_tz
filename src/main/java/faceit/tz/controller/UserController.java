package faceit.tz.controller;

import faceit.tz.service.security.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users")
public class UserController {

    @GetMapping
    public String allUsers() {
        return "html/user/all-users";
    }

    @GetMapping("{id}")
    public String allUserInfo() {
        return "html/user/detail-user";
    }
}
