package faceit.tz.service;

import faceit.tz.model.Book;
import faceit.tz.repository.BookRepository;
import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Book findById(Long id) throws NotFoundException {
        return bookRepository.findById(id).orElseThrow(() -> new NotFoundException("book not exists"));
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public Book findByName(String name) {
        return bookRepository.findByName(name);
    }
}
