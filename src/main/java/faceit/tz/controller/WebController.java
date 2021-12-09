package faceit.tz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

    @GetMapping("books")
    public String booksLink(){
        return "html/book/all-books";
    }

    @GetMapping("users")
    public String usersLink() {
        return "html/user/all-users";
    }

    @GetMapping("profile")
    public String profilePage() {
        return "html/user/profile";
    }

}
