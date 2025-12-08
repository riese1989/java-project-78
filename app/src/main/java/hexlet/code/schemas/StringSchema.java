package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    public StringSchema minLength(int minLength) {
        predicate = s -> minLength != 0 && s.length() >= minLength;

        checks.put("minLength", predicate);

        return this;
    }

    public StringSchema contains(String subString) {
        predicate = s -> subString == null || subString.isEmpty() || s.contains(subString);

        checks.put("contains", predicate);

        return this;
    }

    public StringSchema required() {
        predicate = s -> s != null && !s.isEmpty();

        checks.put("required", predicate);

        return this;
    }
}
