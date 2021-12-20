package faceit.tz.controller.rest;

import faceit.tz.model.Book;
import faceit.tz.model.Reader;
import faceit.tz.model.User;
import faceit.tz.model.dto.ReaderDto;
import faceit.tz.model.mapper.ReaderMapper;
import faceit.tz.service.BookService;
import faceit.tz.service.ReaderService;
import faceit.tz.service.UserService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileRestController {

    private final UserService userService;
    private final ReaderService readerService;
    private final BookService bookService;

    public ProfileRestController(UserService userService, ReaderService readerService, BookService bookService) {
        this.userService = userService;
        this.readerService = readerService;
        this.bookService = bookService;
    }

    @GetMapping
    public List<ReaderDto> viewProfile(HttpServletRequest request) {
        User user = userService.findByUsername(request.getRemoteUser());
        List<Reader> g = readerService.findAll()
                .stream()
                .filter(reader -> reader.getUser().equals(user))
                .collect(Collectors.toList());
        return ReaderMapper.INSTANCE.toDtoList(g);
    }

    @PostMapping
    public ResponseEntity<ReaderDto> addBookToUser(@RequestBody @Valid ReaderDto readerDto, HttpServletRequest request) throws NotFoundException {
        Book book = bookService.findByName(readerDto.getBook());
        User user = userService.findByUsername(request.getRemoteUser());

        userService.addBookToUser(book.getId(), user.getId());

        return new ResponseEntity<>(readerDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ReaderDto editReader(@PathVariable(name = "id") long id) throws NotFoundException {
        Reader reader = readerService.findById(id);
        return ReaderMapper.INSTANCE.toDto(reader);
    }

    @DeleteMapping
    public ResponseEntity<ReaderDto> removeBookFromUser(@RequestBody ReaderDto readerDto, HttpServletRequest request) throws NotFoundException {
        Book book = bookService.findByName(readerDto.getBook());
        User user = userService.findByUsername(request.getRemoteUser());

        userService.removeBookFromUser(book.getId(), user.getId());

        return new ResponseEntity<>(readerDto, HttpStatus.NO_CONTENT);
    }
}
