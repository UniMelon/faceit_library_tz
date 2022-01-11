package faceit.tz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ReaderDto {
    private Long id;

    @Size(min = 5, max = 32, message = "{validation.size.username}")
    private String username;

    @Size(max = 32, message = "{validation.book.size.name}")
    private String book;

    @PastOrPresent(message = "{validation.book.pastOrPresent.date}")
    private LocalDate createdOn;
}
