package faceit.tz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity(name = "user_books")
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "created_on")
    private LocalDate createdOn;

    public Reader() {
    }

    public Reader(Long id, User user, Book book, LocalDate createdOn) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.createdOn = createdOn;
    }

    public Reader(User user, Book book, LocalDate createdOn) {
        this.user = user;
        this.book = book;
        this.createdOn = createdOn;
    }
}
