package faceit.tz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ReaderDto {
    private Long id;
    private String username;
    private String book;
    private LocalDate createdOn;
}
