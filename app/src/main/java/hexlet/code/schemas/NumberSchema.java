package hexlet.code.schemas;

import java.util.Objects;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema positive() {
        predicate = n -> n == null || n > 0;

        addCheck("positive", predicate);

        expression += ".positive()";

        return this;
    }

    public NumberSchema range(int minRange, int maxRange) {
        predicate = n -> n >= minRange && n <= maxRange;

        addCheck("range", predicate);

        expression += ".range(" + minRange + "," + maxRange + ")";

        return this;
    }

    public NumberSchema required() {
        predicate = Objects::nonNull;

        addCheck("required", predicate);

        expression += ".required()";

        return this;
    }

    @Override
    protected void exp() {
        expression = "new NumberSchema()";
    }
}
