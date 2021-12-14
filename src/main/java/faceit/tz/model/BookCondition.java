package faceit.tz.model;

import java.util.Locale;

public enum BookCondition {
    TERRIBLE("terrible"),
    AVERAGE("average"),
    GOOD("good");

    private final String description;

    BookCondition(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static BookCondition of(String value) {
        return BookCondition.valueOf(value.toUpperCase(Locale.ROOT));
    }
}
