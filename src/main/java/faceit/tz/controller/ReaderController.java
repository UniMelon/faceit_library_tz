package faceit.tz.controller;

import faceit.tz.service.BookService;
import faceit.tz.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("readers")
public class ReaderController {

    private final UserService userService;
    private final BookService bookService;

    public ReaderController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping
    public String allReaders(Model model) {
        model.addAttribute("usersList", userService.findAll());
        model.addAttribute("booksList", bookService.findAll());
        return "html/user/all-readers";
    }

//    @GetMapping("profile")
//    public String profilePage() {
//        return "html/user/profile";
//    }
}
