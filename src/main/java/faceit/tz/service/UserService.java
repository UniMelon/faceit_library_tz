package faceit.tz.service;

import faceit.tz.model.Book;
import faceit.tz.model.Reader;
import faceit.tz.model.Role;
import faceit.tz.model.User;
import faceit.tz.repository.ReaderRepository;
import faceit.tz.repository.RoleRepository;
import faceit.tz.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BookService bookService;
    private final ReaderRepository readerRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       BookService bookService, ReaderRepository readerRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bookService = bookService;
        this.readerRepository = readerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll(Optional<Integer> pageNo, Optional<Integer> pageSize) {
        PageRequest pageable = PageRequest.of(pageNo.orElse(0), pageSize.orElse(15));

        Page<User> posts = userRepository.findAll(pageable);
        return posts.getContent();
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

            Role roleUser = roleRepository.findByName("ROLE_USER");
            user.setRoles(Set.of(roleUser));
        }
        userRepository.save(user);
    }

    //fix
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);

        if (user != null) return user;
        else return null;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = findByUsername(s);

        if(user == null) throw new UsernameNotFoundException("User with username %s not found!".formatted(s));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

    }
}
