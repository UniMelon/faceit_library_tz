package faceit.tz.controller;

import faceit.tz.service.BookService;
import faceit.tz.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String allReaders(Pageable pageable, Model model) {
        model.addAttribute("usersList", userService.findAll(pageable));
        model.addAttribute("booksList", bookService.findAll(pageable));
        return "html/user/all-readers";
    }


}
