package faceit.tz.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Data
@Entity(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Date calendarDate;
    private String condition;
    private String readerId;

    public Book() {
    }

    public Book(String name, Date calendarDate, String condition, String readerId) {
        this.name = name;
        this.calendarDate = calendarDate;
        this.condition = condition;
        this.readerId = readerId;
    }

    public Book(Long id, String name, Date calendarDate, String condition, String readerId) {
        this.id = id;
        this.name = name;
        this.calendarDate = calendarDate;
        this.condition = condition;
        this.readerId = readerId;
    }
}