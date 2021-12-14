package faceit.tz.controller;

import faceit.tz.service.BookService;
import faceit.tz.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/readers")
public class ReaderController {

    private final UserService userService;
    private final BookService bookService;

    public ReaderController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping
    public String allReaders(@RequestParam Optional<Integer> pageNo, @RequestParam Optional<Integer> pageSize,
                             Model model) {
        model.addAttribute("usersList", userService.findAll(pageNo, pageSize));
        model.addAttribute("booksList", bookService.findAll(pageNo, pageSize));
        return "html/user/all-readers";
    }

//    @GetMapping("profile")
//    public String profilePage() {
//        return "html/user/profile";
//    }
}
