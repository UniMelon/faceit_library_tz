package faceit.tz.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@RequiredArgsConstructor
@SQLDelete(sql = "UPDATE books SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
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

    @Column(name = "deleted")
    private boolean deleted;

    @OneToMany(mappedBy = "book")
    private Set<Reader> users = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id)
                && Objects.equals(name, book.name)
                && Objects.equals(condition, book.condition)
                && Objects.equals(calendarDate, book.calendarDate)
                && Objects.equals(deleted, book.deleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, condition, calendarDate, deleted);
    }
}