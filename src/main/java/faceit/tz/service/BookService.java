package faceit.tz.service;

import faceit.tz.model.Book;
import faceit.tz.model.User;
import faceit.tz.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(Optional<Integer> pageNo, Optional<Integer> pageSize) {
        PageRequest pageable = PageRequest.of(pageNo.orElse(0), pageSize.orElse(15));

        Page<Book> posts = bookRepository.findAll(pageable);
        return posts.getContent();
    }

    public Optional<Book> findById(Long id) {
        if(bookRepository.findById(id).isEmpty())
            return Optional.empty();
        else
            return bookRepository.findById(id);
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public void deleteById(Long id) {
        Optional<Book> bookOptional = findById(id);
        if (bookOptional.isPresent())
            bookRepository.deleteById(id);
    }

    public Book findByName(String name) {
        Book book = bookRepository.findByName(name);

        if (book != null) return book;
        else return null;
    }
}
