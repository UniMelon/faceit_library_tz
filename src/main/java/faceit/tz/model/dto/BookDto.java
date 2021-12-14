package faceit.tz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BookDto {
    private Long id;
    private String name;
    private String condition;
    private LocalDate calendarDate;
}
