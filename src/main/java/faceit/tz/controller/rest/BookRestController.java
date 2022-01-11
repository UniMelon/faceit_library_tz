package faceit.tz.controller.rest;

import faceit.tz.model.entity.Book;
import faceit.tz.model.dto.BookDto;
import faceit.tz.model.mapper.BookMapper;
import faceit.tz.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Page<BookDto> getAllBooks(Pageable pageable) {
        Page<Book> bookPage = bookService.findAll(pageable);
        return bookPage.map(BookMapper.INSTANCE::toDto);
    }

}
