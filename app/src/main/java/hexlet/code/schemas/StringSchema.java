package hexlet.code.schemas;

import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {
    private Predicate<String> predicate;

    public StringSchema minLength(int minLength) {
        predicate = s -> minLength != 0 && s.length() >= minLength;

        addCheck("minLength", predicate);

        return this;
    }

    public StringSchema contains(String subString) {
        predicate = s -> subString == null || subString.isEmpty() || s.contains(subString);

        addCheck("contains", predicate);

        return this;
    }

    public StringSchema required() {
        predicate = s -> s != null && !s.isEmpty();

        addCheck("required", predicate);

        return this;
    }
}
