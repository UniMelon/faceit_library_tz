package faceit.tz.model;

import lombok.Data;

@Data
public class JsonResponse {
    private String status;
    private Object result;

    public JsonResponse() {
    }
}
