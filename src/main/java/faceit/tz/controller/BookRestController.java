package faceit.tz.controller;

import faceit.tz.model.Book;
import faceit.tz.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path="/rest")
    public List<Book> getAllObjects() {
        return bookService.findAll();
    }

}
