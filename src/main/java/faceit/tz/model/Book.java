package faceit.tz.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "condition")
    private String condition;

    @Column(name = "calendar_date")
    private LocalDate calendarDate;

    @OneToMany(mappedBy = "book")
    private List<Reader> users = new ArrayList<>();

    public Book() {
    }
}