package faceit.tz.service.security;

import faceit.tz.model.Book;
import faceit.tz.model.security.Role;
import faceit.tz.model.security.User;
import faceit.tz.repository.security.UserRepository;
import faceit.tz.service.BookService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BookService bookService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BookService bookService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bookService = bookService;
        this.passwordEncoder = passwordEncoder;
    }

//    @PostConstruct
//    private void initData() {
//        if (findAll().isEmpty()) {
//            User user = new User("username", "password", true, Role.USER);
//            save(user);
//        }
//    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<Book> findBooksByUserId(Long user_id) {
        Optional<User> optionalUser = findById(user_id);

        if (optionalUser.isPresent()) return userRepository.findBooksByUserId(user_id);
        return List.of();
    }

    public void addBookToUser(Long book_id, Long user_id) {
        User user = findById(user_id).get();
        Book book = bookService.findById(book_id).get();

        user.getBooksList().add(book);
    }

    public void removeBookFromUser(Long book_id, Long user_id) {
        User user = findById(user_id).get();
        Book book = bookService.findById(book_id).get();

        user.getBooksList().remove(book);
    }

    public Optional<User> findById(Long id) {
        if(userRepository.findById(id).isEmpty()) return Optional.empty();
        else return userRepository.findById(id);
    }

    public boolean isUserExist(User user) {
        User userFromDB = findByUsername(user.getUsername());
        if (userFromDB != null) return true;
        else return false;
    }

    public void save(User user) {
        if (!user.isActive()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setActive(true);
            user.setRole(Role.USER);
        }
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user != null) return user;
        else return null;
    }

}
