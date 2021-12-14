package faceit.tz.controller.rest;

import faceit.tz.model.Reader;
import faceit.tz.model.dto.ReaderDto;
import faceit.tz.service.BookService;
import faceit.tz.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/readers")
public class ReaderRestController {

    private final UserService userService;
    private final BookService bookService;

    public ReaderRestController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping
    public List<ReaderDto> get_allReaders() {
        return userService.findAllReaders();
    }

//    @GetMapping("/profile")
//    public User viewProfile(HttpServletRequest request, Model model) {
//        User user = userService.findByUsername(request.getRemoteUser());
//        model.addAttribute("id", user.getId());
//        return user;
//    }
}
