package hexlet.code.schemas;

public class StringSchema extends BaseSchemaRequired<StringSchema> implements BaseSchema<String> {
    @SuppressWarnings("HiddenField")
    private int minLength;
    @SuppressWarnings("HiddenField")
    private String subString;

    public final StringSchema minLength(int minLength) {
        this.minLength = minLength;

        return this;
    }

    public final StringSchema contains(String subString) {
        this.subString = subString;

        return this;
    }

    @Override
    public final boolean isValid(String checkedString) {
        if (isRequired() && (checkedString == null || checkedString.isEmpty())) {
            return false;
        }

        if (checkedString == null || checkedString.isEmpty()) {
            return true;
        }

        if (minLength != 0 && checkedString.length() < minLength) {
            return false;
        }

        if (subString == null || subString.isEmpty()) {
            return true;
        }

        return checkedString.contains(subString);
    }
}
