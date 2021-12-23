package faceit.tz.service;

import faceit.tz.controller.exception.InvalidTokenException;
import faceit.tz.controller.exception.UserAlreadyExistException;
import faceit.tz.model.Book;
import faceit.tz.model.Reader;
import faceit.tz.model.Role;
import faceit.tz.model.User;
import faceit.tz.repository.ReaderRepository;
import faceit.tz.repository.RoleRepository;
import faceit.tz.repository.UserRepository;
import faceit.tz.service.mail.EmailSender;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.*;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BookService bookService;
    private final ReaderRepository readerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private EmailSender emailSender;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       BookService bookService, ReaderRepository readerRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bookService = bookService;
        this.readerRepository = readerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public void addBookToUser(Long book_id, Long user_id) throws NotFoundException {
        User user = findById(user_id);
        Book book = bookService.findById(book_id);

        if(user != null && book != null) {
            Reader reader = new Reader(user, book, LocalDate.now());
            user.getBooks().add(reader);
            book.getUsers().add(reader);
            readerRepository.save(reader);
        }
    }

    public void removeBookFromUser(Long book_id, Long user_id) throws NotFoundException {
        User user = findById(user_id);
        Book book = bookService.findById(book_id);

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

    public User findById(Long id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("user not exists"));
    }

    public boolean isUserExist(String username, String email) {
        return userRepository.findByEmail(email) != null || findByUsername(username) != null;
    }

    public void register(User user) throws MessagingException {
        if (isUserExist(user.getUsername(), user.getEmail()))
            throw new UserAlreadyExistException("User already exists!");

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role roleUser = roleRepository.findByName("ROLE_USER");
        user.setRoles(Set.of(roleUser));
        user.setActivationCode(UUID.randomUUID().toString());

        userRepository.save(user);

        sendRegistrationConfirmationEmail(user);
    }

    private void sendRegistrationConfirmationEmail(User user) throws MessagingException {
        String message = String.format("Welcome, %s!\nPlease, visit next link for verify registration proccess: " +
                "http://localhost:8080/api/v1/register/%s", user.getUsername(), user.getActivationCode());

        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("username", user.getUsername());
        templateModel.put("token", user.getActivationCode());
        templateModel.put("sign", "Java Developer");
        templateModel.put("location", "FaceIT-team");

        emailSender.sendEmail(user.getEmail(), "Activation code", templateModel);
    }

    public void verifyUser(String token) throws InvalidTokenException {
        User user = userRepository.findByActivationCode(token);

        if (Objects.isNull(user)) throw new InvalidTokenException("Token is not found!");

        user.setActive(true);
        user.setActivationCode(null);
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = findByUsername(s);
        boolean enabled = !user.isActive();

        if (user == null) throw new UsernameNotFoundException("User %s not found!".formatted(s));

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getName())));

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .disabled(enabled)
                .authorities(authorities).build();

        return userDetails;
    }
}
