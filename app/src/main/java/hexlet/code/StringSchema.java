package hexlet.code;

public class StringSchema {
    private boolean isRequired;
    private int minLength;
    private String subString;

    public StringSchema required() {
        isRequired = true;

        return this;
    }

    public StringSchema minLength(int minLength) {
        this.minLength = minLength;

        return this;
    }

    public StringSchema contains(String subString) {
        this.subString = subString;

        return this;
    }

    public boolean isValid(String checkedString) {
        if (isRequired && checkedString == null) {
            return false;
        }

        if (minLength != 0 && checkedString.length() < minLength) {
            return false;
        }

        return subString == null || checkedString.contains(subString);
    }
}
