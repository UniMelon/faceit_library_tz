package faceit.tz.service;

import faceit.tz.model.Book;
import faceit.tz.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Optional<Book> findById(Long id) {
        if(bookRepository.findById(id).isEmpty())
            return Optional.empty();
        else
            return bookRepository.findById(id);
    }

    public List<Book> findByName(String name) {
        List<Book> bookList = bookRepository.findByName(name);

        if (!bookList.isEmpty()) return bookList;
        else return List.of();
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public void deleteById(Long id) {
        Optional<Book> bookOptional = findById(id);
        if (bookOptional.isPresent())
            bookRepository.deleteById(id);
    }
}
