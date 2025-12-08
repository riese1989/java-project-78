package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {
    public StringSchema minLength(int minLength) {
        predicate = s -> minLength != 0 && s.length() >= minLength;

        addCheck("minLength", predicate);

        expression += ".minLength(" + minLength + ")";
        return this;
    }

    public StringSchema contains(String subString) {
        predicate = s -> subString == null || subString.isEmpty() || s.contains(subString);

        addCheck("contains", predicate);
        expression += ".contains(" + subString + ")";
        return this;
    }

    public StringSchema required() {
        predicate = s -> s != null && !s.isEmpty();

        addCheck("required", predicate);
        expression += ".required()";

        return this;
    }

    @Override
    protected void exp() {
        expression = "new StringSchema()";
    }
}
