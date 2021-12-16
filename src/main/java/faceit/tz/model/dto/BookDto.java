package faceit.tz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BookDto {
    private Long id;

    @Size(max = 32, message = "name must be up to 32 characters!")
    private String name;

    @NotBlank(message = "condition must not be empty!")
    private String condition;

    @PastOrPresent(message = "this date has not come yet!")
    private LocalDate calendarDate;
}
