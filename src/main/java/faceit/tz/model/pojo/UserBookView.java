package faceit.tz.model.pojo;

import lombok.Data;

import java.sql.Date;

@Data
public class UserBookView {
    private Long id;
    private String username;
    private String book;
    private Date startedOn;

    public UserBookView(Long id, String username, String book, Date startedOn) {
        this.id = id;
        this.username = username;
        this.book = book;
        this.startedOn = startedOn;
    }
}
