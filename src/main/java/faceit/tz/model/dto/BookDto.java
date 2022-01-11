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

    @Size(max = 32, message = "{validation.book.size.name}")
    private String name;

    @NotBlank(message = "{validation.book.notblank.condition}")
    private String condition;

    @PastOrPresent(message = "{validation.book.pastOrPresent.date}")
    private LocalDate calendarDate;
}
