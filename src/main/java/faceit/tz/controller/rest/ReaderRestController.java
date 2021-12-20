package faceit.tz.controller.rest;

import faceit.tz.model.Reader;
import faceit.tz.model.dto.ReaderDto;
import faceit.tz.model.mapper.ReaderMapper;
import faceit.tz.service.ReaderService;
import faceit.tz.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/readers")
public class ReaderRestController {

    private final ReaderService readerService;
    private final UserService userService;

    public ReaderRestController(ReaderService readerService, UserService userService) {
        this.readerService = readerService;
        this.userService = userService;
    }

    @GetMapping
    public Page<ReaderDto> get_allReaders(Pageable pageable) {
        Page<Reader> bookPage = readerService.findAll(pageable);
        return bookPage.map(ReaderMapper.INSTANCE::toDto);
    }
}
