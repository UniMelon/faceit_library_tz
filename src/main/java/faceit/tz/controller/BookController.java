package faceit.tz.controller;

import faceit.tz.model.BookCondition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/books")
public class BookController {

    @GetMapping
    public String allBooks(Model model) {
        model.addAttribute("condition", BookCondition.values());
        return "html/book/all-books";
    }

}
