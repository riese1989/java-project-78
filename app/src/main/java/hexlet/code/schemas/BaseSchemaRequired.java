package hexlet.code.schemas;

public abstract class BaseSchemaRequired<T extends BaseSchema> {
    private boolean isRequired;

    public final T required() {
        isRequired = true;

        return (T) this;
    }

    public final boolean isRequired() {
        return isRequired;
    }
}
