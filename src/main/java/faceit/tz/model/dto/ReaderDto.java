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

    @Size(max = 32, message = "username must be up to 32 characters!")
    private String username;

    @Size(max = 32, message = "book name must be up to 32 characters!")
    private String book;

    @PastOrPresent(message = "this date has not come yet!")
    private LocalDate createdOn;
}
