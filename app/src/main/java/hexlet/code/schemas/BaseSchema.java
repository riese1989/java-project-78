package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected Predicate<T> predicate;
    protected boolean required = false;

    protected final void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }

    public final boolean isValid(T value) {
        for (var entry : checks.entrySet()) {
            var check = entry.getValue();

            if (!check.test(value)) {
                return false;
            }
        }

        return true;
    }
}
