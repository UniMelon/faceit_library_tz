package faceit.tz.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String condition;
    private Date calendarDate;

    @OneToMany(mappedBy = "book")
    private List<UserBook> users = new ArrayList<>();

    public Book() {
    }
}