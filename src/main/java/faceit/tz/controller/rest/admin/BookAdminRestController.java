package faceit.tz.controller.rest.admin;

import faceit.tz.model.Book;
import faceit.tz.model.dto.BookDto;
import faceit.tz.model.mapper.BookMapper;
import faceit.tz.service.BookService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/books")
public class BookAdminRestController {

    private final BookService bookService;

    public BookAdminRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public BookDto postSaveBook(@RequestBody Book book) {
        bookService.save(book);
        return BookMapper.INSTANCE.toDto(book);
    }

    @GetMapping("/{id}")
    public BookDto editBook(@PathVariable(name = "id") long id) throws NotFoundException {
        Book book = bookService.findById(id).get();
        return BookMapper.INSTANCE.toDto(book);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteBook(@PathVariable(name = "id") long id) throws NotFoundException {
        bookService.deleteById(id);
        return HttpStatus.NO_CONTENT;
    }
}
