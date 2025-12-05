package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer, NumberSchema> {
    private boolean isPositive;
    private Range range;

    public NumberSchema positive() {
        isPositive = true;

        return this;
    }

    public NumberSchema range(int minRange, int maxRange) {
        range = new Range(minRange, maxRange);

        return this;
    }

    @Override
    public boolean isVerified(Integer checkedNumber) {
        if (isRequired() && checkedNumber == null) {
            return false;
        }

        if (checkedNumber == null) {
            return true;
        }

        if (isPositive && checkedNumber <= 0) {
            return false;
        }

        if (range == null) {
            return true;
        }

        return checkedNumber >= range.minRange && checkedNumber <= range.maxRange;
    }

    private record Range(int minRange, int maxRange) { }
}
