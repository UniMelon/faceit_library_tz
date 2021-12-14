package faceit.tz.controller.rest;

import faceit.tz.model.dto.BookDto;
import faceit.tz.model.mapper.BookMapper;
import faceit.tz.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookDto> getAllObjects(@RequestParam Optional<Integer> pageNo,
                                       @RequestParam Optional<Integer> pageSize) {

        return bookService.findAll(pageNo, pageSize)
                .stream()
                .map(BookMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

}
