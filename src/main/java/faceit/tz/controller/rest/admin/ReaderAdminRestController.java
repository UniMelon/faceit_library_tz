package faceit.tz.controller.rest.admin;

import faceit.tz.model.Book;
import faceit.tz.model.Reader;
import faceit.tz.model.User;
import faceit.tz.model.dto.ReaderDto;
import faceit.tz.model.mapper.ReaderMapper;
import faceit.tz.service.BookService;
import faceit.tz.service.ReaderService;
import faceit.tz.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/readers")
public class ReaderAdminRestController {

    private final UserService userService;
    private final BookService bookService;
    private final ReaderService readerService;

    public ReaderAdminRestController(UserService userService, BookService bookService,
                                     ReaderService readerService) {
        this.userService = userService;
        this.bookService = bookService;
        this.readerService = readerService;
    }

    @PostMapping
    public HttpStatus addBookToUser(@RequestBody ReaderDto readerDto) {
        Book book = bookService.findByName(readerDto.getBook());
        User user = userService.findByUsername(readerDto.getUsername());

        userService.addBookToUser(book.getId(), user.getId());

        return HttpStatus.OK;
    }

    @GetMapping("/{id}")
    public ReaderDto editReader(@PathVariable(name = "id") long id) {
        Reader reader = readerService.findById(id).get();
        ReaderDto readerDto = ReaderMapper.INSTANCE.toDto(reader);
        return readerDto;
    }

    @DeleteMapping
    public HttpStatus removeBookFromUser(@RequestBody ReaderDto readerDto) {

        Book book = bookService.findByName(readerDto.getBook());
        User user = userService.findByUsername(readerDto.getUsername());

        userService.removeBookFromUser(book.getId(), user.getId());

        return HttpStatus.NO_CONTENT;
    }
}
