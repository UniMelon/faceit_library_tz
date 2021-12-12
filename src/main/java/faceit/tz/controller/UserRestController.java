package faceit.tz.controller;

import faceit.tz.model.Book;
import faceit.tz.model.pojo.UserBookView;
import faceit.tz.model.User;
import faceit.tz.service.BookService;
import faceit.tz.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserRestController {

    private final UserService userService;
    private final BookService bookService;

    public UserRestController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping
    public List<UserBookView> get_allReaders() {
        return userService.findAllReaders();
    }

//    @GetMapping("{id}")
//    public List<Book> get_booksByUserId(@PathVariable(value = "id") long user_id) {
//        return userService.findBooksByUserId(user_id);
//    }

    @GetMapping("/profile")
    public User viewProfile(HttpServletRequest request, Model model) {
        User user = userService.findByUsername(request.getRemoteUser());
        model.addAttribute("id", user.getId());
        return user;
    }

//    @GetMapping("{id}/add-book")
//    public String get_addBookToUser(@PathVariable(value = "id") long user_id, Model model) {
//        model.addAttribute("id", user_id);
//
//        User user = userService.findById(user_id).get();
//        List<Book> bookList = bookService.findAll();
//        bookList.removeAll(user.getBooksList());
//
//        model.addAttribute("bookList", bookList);
//        return "";
//    }

    @PostMapping(path="add-book")
    public HttpStatus post_addBookToUser(@RequestParam long user_id, @RequestParam long book_id) {
        Optional<Book> bookOptional = bookService.findById(book_id);
        Optional<User> userOptional = userService.findById(user_id);

        if(bookOptional.isPresent() && userOptional.isPresent())
            userService.addBookToUser(book_id, user_id);

        return HttpStatus.OK;
    }

    @DeleteMapping(path="remove-book")
    public HttpStatus post_removeBookFromUser(@RequestParam long user_id,
                                              @RequestParam long book_id) {

        Optional<Book> bookOptional = bookService.findById(book_id);
        Optional<User> userOptional = userService.findById(user_id);

        if(bookOptional.isPresent() && userOptional.isPresent())
            userService.removeBookFromUser(book_id, user_id);

        return HttpStatus.NO_CONTENT;
    }

}
