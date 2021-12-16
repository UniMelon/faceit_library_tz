package faceit.tz.controller.rest.admin;

import faceit.tz.model.Book;
import faceit.tz.model.dto.BookDto;
import faceit.tz.model.mapper.BookMapper;
import faceit.tz.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/admin/books")
public class BookAdminRestController {

    private final BookService bookService;

    public BookAdminRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDto> postSaveBook(@RequestBody @Valid BookDto bookDto) {
        Book book = BookMapper.INSTANCE.toEntity(bookDto);
        bookService.save(book);
        return new ResponseEntity<>(bookDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public BookDto editBook(@PathVariable(name = "id") long id) {
        Book book = bookService.findById(id).get();
        return BookMapper.INSTANCE.toDto(book);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteBook(@PathVariable(name = "id") long id) {
        bookService.deleteById(id);
        return HttpStatus.NO_CONTENT;
    }
}
