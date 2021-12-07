package faceit.tz.controller;

import faceit.tz.model.Book;
import faceit.tz.model.security.User;
import faceit.tz.service.BookService;
import faceit.tz.service.security.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final BookService bookService;

    public UserController(UserService userService, BookService bookService) {
        this.userService = userService;
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<List<User>> get_allUsers() {
        List<User> userList = userService.findAll();

        if (userList.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<List<Book>> get_allBookByUserId(@PathVariable(value = "id") long user_id) {
        List<Book> bookList = userService.findBooksByUserId(user_id);

        if (bookList.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(bookList, HttpStatus.OK);
    }

    @GetMapping(path = "{id}/add-book")
    public String get_addBookToUser(@PathVariable(value = "id") long user_id, Model model) {
        model.addAttribute("id", user_id);

        User user = userService.findById(user_id).get();
        List<Book> bookList = bookService.findAll();
        bookList.removeAll(user.getBookList());

        model.addAttribute("bookList", bookList);
        return "";
    }

    @PostMapping(path="{id}/add-book")
    public String post_addBookToUser(@PathVariable(value = "id") long user_id, @RequestParam long book_id) {
        Optional<Book> bookOptional = bookService.findById(book_id);
        Optional<User> userOptional = userService.findById(user_id);

        if(bookOptional.isPresent() && userOptional.isPresent())
            userService.addBookToUser(book_id, user_id);

        return "redirect:/users/{id}";
    }


}
