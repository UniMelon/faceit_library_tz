package faceit.tz.controller.rest.admin;

import faceit.tz.model.Book;
import faceit.tz.model.User;
import faceit.tz.model.dto.ReaderDto;
import faceit.tz.service.BookService;
import faceit.tz.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/readers")
public class ReaderAdminRestController {

    private final UserService userService;
    private final BookService bookService;

    public ReaderAdminRestController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public HttpStatus addBookToUser(@RequestBody ReaderDto readerDto) {
        Book book = bookService.findByName(readerDto.getBook());
        User user = userService.findByUsername(readerDto.getUsername());

        userService.addBookToUser(book.getId(), user.getId());

        return HttpStatus.OK;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping
    public HttpStatus removeBookFromUser(@RequestBody ReaderDto readerDto) {

        Book book = bookService.findByName(readerDto.getBook());
        User user = userService.findByUsername(readerDto.getUsername());

        userService.removeBookFromUser(book.getId(), user.getId());

        return HttpStatus.NO_CONTENT;
    }
}
