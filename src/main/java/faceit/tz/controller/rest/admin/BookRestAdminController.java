package faceit.tz.controller.rest.admin;

import faceit.tz.model.Book;
import faceit.tz.model.dto.BookDto;
import faceit.tz.model.mapper.BookMapper;
import faceit.tz.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/api/books")
public class BookRestAdminController {

    private final BookService bookService;

    public BookRestAdminController(BookService bookService) {
        this.bookService = bookService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("save")
    public BookDto postSaveBook(@RequestBody Book book) {
        bookService.save(book);
        return BookMapper.INSTANCE.toDto(book);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("remove/{id}")
    public HttpStatus postSaveBook(@PathVariable(name = "id") long id) {
        bookService.deleteById(id);
        return HttpStatus.NO_CONTENT;
    }
}
