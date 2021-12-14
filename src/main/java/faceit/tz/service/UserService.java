package faceit.tz.service;

import faceit.tz.model.Book;
import faceit.tz.model.Reader;
import faceit.tz.model.Role;
import faceit.tz.model.User;
import faceit.tz.model.dto.ReaderDto;
import faceit.tz.model.mapper.ReaderMapper;
import faceit.tz.repository.ReaderRepository;
import faceit.tz.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BookService bookService;
    private final ReaderRepository readerRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BookService bookService, ReaderRepository readerRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.bookService = bookService;
        this.readerRepository = readerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public List<ReaderDto> findAllReaders() {
        List<ReaderDto> viewList = new ArrayList<>();

        for(Reader reader : readerRepository.findAll()) {
            Long id = reader.getId();
            User user = reader.getUser();
            Book book = reader.getBook();
            LocalDate date = reader.getCreatedOn();
            viewList.add(ReaderMapper.INSTANCE.toDto(new Reader(id, user, book, date)));

        }
        return viewList;
    }

    public void addBookToUser(Long book_id, Long user_id) {
        User user = findById(user_id).get();
        Book book = bookService.findById(book_id).get();

        if(user != null && book != null) {
            Reader reader = new Reader(user, book, LocalDate.now());
            user.getBooks().add(reader);
            book.getUsers().add(reader);
            readerRepository.save(reader);
        }
    }

    public void removeBookFromUser(Long book_id, Long user_id) {
        User user = findById(user_id).get();
        Book book = bookService.findById(book_id).get();

        if(user != null && book != null) {
            for (Iterator<Reader> iterator = user.getBooks().iterator(); iterator.hasNext(); ) {
                Reader reader = iterator.next();

                if (reader.getUser().equals(user) && reader.getBook().equals(book)) {
                    iterator.remove();
                    reader.getBook().getUsers().remove(reader);
                    readerRepository.delete(reader);
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
