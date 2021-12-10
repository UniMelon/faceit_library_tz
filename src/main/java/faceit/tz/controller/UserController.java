package faceit.tz.controller;

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

    @GetMapping("profile")
    public String profilePage() {
        return "html/user/profile";
    }
}
