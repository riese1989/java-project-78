package hexlet.code.schemas;

public class NumberSchema extends BaseSchemaRequired<NumberSchema> implements BaseSchema<Integer>{
    private boolean isPositive;
    private Range range;

    public final NumberSchema positive() {
        isPositive = true;

        return this;
    }

    public final NumberSchema range(int minRange, int maxRange) {
        range = new Range(minRange, maxRange);

        return this;
    }

    @Override
    public final boolean isValid(Integer checkedNumber) {
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

    private record Range(int minRange, int maxRange) {}
}
