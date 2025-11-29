package hexlet.code.schemas;

public class StringSchema extends BaseSchemaRequired<StringSchema> implements BaseSchema<String> {
    private int minLength;
    private String subString;

    public StringSchema minLength(int minLength) {
        this.minLength = minLength;

        return this;
    }

    public StringSchema contains(String subString) {
        this.subString = subString;

        return this;
    }

    @Override
    public boolean isValid(String checkedString) {
        if (isRequired() && checkedString == null) {
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
