package faceit.tz.controller;

import faceit.tz.service.BookService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final BookService bookService;

    public ProfileController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String profilePage(Pageable pageable, Model model) {
        model.addAttribute("booksList", bookService.findAll(pageable));
        return "html/user/profile";
    }
}
