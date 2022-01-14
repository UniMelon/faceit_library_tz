package faceit.tz.service;

import faceit.tz.model.entity.Book;
import faceit.tz.repository.BookRepository;
import javassist.NotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Cacheable(value = "cache-book")
    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Cacheable(value = "cache-book", key = "#id")
    public Book findById(Long id) throws NotFoundException {
        return bookRepository.findById(id).orElseThrow(() -> new NotFoundException("book not exists"));
    }

    @CachePut(value = "cache-book", key = "#book.name")
    public void save(Book book) {
        bookRepository.save(book);
    }

    @CacheEvict(value = "cache-book", allEntries = true)
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public Book findByName(String name) {
        return bookRepository.findByName(name);
    }
}
