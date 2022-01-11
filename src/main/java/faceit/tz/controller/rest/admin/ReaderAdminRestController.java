package faceit.tz.controller.rest.admin;

import faceit.tz.model.entity.Book;
import faceit.tz.model.entity.Reader;
import faceit.tz.model.entity.User;
import faceit.tz.model.dto.ReaderDto;
import faceit.tz.model.mapper.ReaderMapper;
import faceit.tz.service.BookService;
import faceit.tz.service.ReaderService;
import faceit.tz.service.UserService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<ReaderDto> addBookToUser(@RequestBody @Valid ReaderDto readerDto) throws NotFoundException {
        Book book = bookService.findByName(readerDto.getBook());
        User user = userService.findByUsername(readerDto.getUsername());

        userService.addBookToUser(book.getId(), user.getId());

        return new ResponseEntity<>(readerDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ReaderDto editReader(@PathVariable(name = "id") long id) throws NotFoundException {
        Reader reader = readerService.findById(id);
        return ReaderMapper.INSTANCE.toDto(reader);
    }

    @DeleteMapping
    public ResponseEntity<ReaderDto> removeBookFromUser(@RequestBody ReaderDto readerDto) throws NotFoundException {

        Book book = bookService.findByName(readerDto.getBook());
        User user = userService.findByUsername(readerDto.getUsername());

        userService.removeBookFromUser(book.getId(), user.getId());

        return new ResponseEntity<>(readerDto, HttpStatus.NO_CONTENT);
    }
}
