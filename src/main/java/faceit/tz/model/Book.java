package faceit.tz.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Entity(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String condition;
    private LocalDate calendarDate;

    @OneToMany(mappedBy = "book")
    private List<Reader> users = new ArrayList<>();

    public Book() {
    }
}