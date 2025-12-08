package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema positive() {
        predicate = n -> n == null || n > 0;

        addCheck("positive", predicate);

        return this;
    }

    public NumberSchema range(int minRange, int maxRange) {
        predicate = n -> n >= minRange && n <= maxRange;

        addCheck("range", predicate);

        return this;
    }

    public NumberSchema required() {
        predicate = Objects::nonNull;

        addCheck("required", predicate);

        return this;
    }
}
