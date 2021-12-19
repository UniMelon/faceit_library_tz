package faceit.tz.model.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class DatatableDto<T> {
    private int draw;
    private int start;
    private long recordsTotal;
    private long recordsFiltered;
    private Page<T> data;
}
