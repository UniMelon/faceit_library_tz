package faceit.tz.service;

import faceit.tz.model.Reader;
import faceit.tz.repository.ReaderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReaderService {

    private final ReaderRepository readerRepository;

    public ReaderService(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public List<Reader> findAll(Optional<Integer> pageNo, Optional<Integer> pageSize) {
        PageRequest pageable = PageRequest.of(pageNo.orElse(0), pageSize.orElse(15));

        Page<Reader> posts = readerRepository.findAll(pageable);
        return posts.getContent();
    }

    public Optional<Reader> findById(Long id) {
        if(readerRepository.findById(id).isEmpty())
            return Optional.empty();
        else
            return readerRepository.findById(id);
    }

    public void save(Reader reader) {
        readerRepository.save(reader);
    }

    public void deleteById(Long id) {
        Optional<Reader> readerOptional = findById(id);
        if (readerOptional.isPresent())
            readerRepository.deleteById(id);
    }
}
