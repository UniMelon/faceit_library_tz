package faceit.tz.service;

import faceit.tz.model.Book;
import faceit.tz.model.Role;
import faceit.tz.model.User;
import faceit.tz.model.UserBook;
import faceit.tz.model.pojo.UserBookView;
import faceit.tz.repository.UserBookRepository;
import faceit.tz.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BookService bookService;
    private final UserBookRepository userBookRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BookService bookService, UserBookRepository userBookRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bookService = bookService;
        this.userBookRepository = userBookRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<UserBookView> findAllReaders() {
        List<UserBookView> viewList = new ArrayList<>();

        for(UserBook userBook: userBookRepository.findAll()) {
            Long id = userBook.getId();
            String user_name = userRepository.findById(userBook.getUser().getId()).get().getUsername();
            String book_name = bookService.findById(userBook.getBook().getId()).get().getName();
            Date date = userBook.getCreatedOn();
            viewList.add(new UserBookView(id, user_name, book_name, date));

        }
        return viewList;
    }

//    public List<Book> findBooksByUserId(Long user_id) {
//        Optional<User> optionalUser = findById(user_id);
//
//        if (optionalUser.isPresent()) return userRepository.findBooksByUserId(user_id);
//        return List.of();
//    }

    public void addBookToUser(Long book_id, Long user_id) {
        User user = findById(user_id).get();
        Book book = bookService.findById(book_id).get();

        if(user != null && book != null) {
            UserBook userBook = new UserBook(user, book);
            user.getBooks().add(userBook);
            book.getUsers().add(userBook);
        }
    }

    public void removeBookFromUser(Long book_id, Long user_id) {
        User user = findById(user_id).get();
        Book book = bookService.findById(book_id).get();

        if(user != null && book != null) {
            for (Iterator<UserBook> iterator = user.getBooks().iterator(); iterator.hasNext(); ) {
                UserBook userBook = iterator.next();

                if (userBook.getUser().equals(user) && userBook.getBook().equals(book)) {
                    iterator.remove();
                    userBook.getBook().getUsers().remove(userBook);
                    userBook.setUser(null);
                    userBook.setBook(null);
                }
            }
        }
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
