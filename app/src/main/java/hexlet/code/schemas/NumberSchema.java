package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema<Integer> {
    public NumberSchema positive() {
        Predicate<Integer> predicate = n -> n == null || n > 0;

        addCheck("positive", predicate);

        return this;
    }

    public NumberSchema range(int minRange, int maxRange) {
        Predicate<Integer> predicate = n -> n >= minRange && n <= maxRange;

        addCheck("range", predicate);

        return this;
    }

    public NumberSchema required() {
        Predicate<Integer> predicate = Objects::nonNull;

        addCheck("required", predicate);

        return this;
    }
}
