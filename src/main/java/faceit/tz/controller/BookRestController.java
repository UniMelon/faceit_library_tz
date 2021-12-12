package faceit.tz.controller;

import faceit.tz.model.Book;
import faceit.tz.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllObjects() {
        return bookService.findAll();
    }

    @PostMapping("save")
    public Book postSaveBook(@RequestBody Book book) {
        bookService.save(book);
        return book;
    }

    @DeleteMapping("remove/{id}")
    public HttpStatus postSaveBook(@PathVariable(name = "id") long id) {
        bookService.deleteById(id);
        return HttpStatus.NO_CONTENT;
    }

}
