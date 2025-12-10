package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected Predicate<T> predicate;

    protected final void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }

    public final boolean isValid(Object value) {
        for (var entry : checks.entrySet()) {
            var check = entry.getValue();

            if (!check.test((T) value)) {
                return false;
            }
        }

        return true;
    }
}
