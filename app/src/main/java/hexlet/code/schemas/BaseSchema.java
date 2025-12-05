package hexlet.code.schemas;

public abstract class BaseSchema<T, U> {
    private boolean isRequired;

    public final U required() {
        isRequired = true;

        return (U) this;
    }

    public final boolean isRequired() {
        return isRequired;
    }

    public final boolean isValid(T t) {
        if (isRequired && t == null) {
            return false;
        }

        return isVerified(t);
    }

    public abstract boolean isVerified(T t);
}
