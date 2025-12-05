package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String, StringSchema> {
    private int minLength;
    private String subString;

    public StringSchema minLength(int minLengthNew) {
        minLength = minLengthNew;

        return this;
    }

    public StringSchema contains(String subStringNew) {
        subString = subStringNew;

        return this;
    }

    @Override
    public boolean isVerified(String checkedString) {
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
