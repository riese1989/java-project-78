package hexlet.code.schemas;

public class NumberSchema extends BaseSchemaRequired<NumberSchema> implements BaseSchema<Integer>{
    private boolean isPositive;
    private int minRange;
    private int maxRange;

    public NumberSchema positive() {
        isPositive = true;

        return this;
    }

    public NumberSchema range(int minRange, int maxRange) {
        this.minRange = minRange;
        this.maxRange = maxRange;

        return this;
    }

    @Override
    public boolean isValid(Integer checkedNumber) {
        if (isRequired() && checkedNumber == null) {
            return false;
        }

        if (isPositive && checkedNumber != null && checkedNumber <= 0) {
            return false;
        }

        if (checkedNumber == null) {
            return true;
        }

        return checkedNumber >= minRange && checkedNumber <= maxRange;
    }
}
