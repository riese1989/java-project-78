package hexlet.code.schemas;

import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

@SuppressWarnings("unchecked")
public final class MapSchema extends BaseSchema<Map> {
    private Predicate<Map> predicate;

    public MapSchema sizeof(int size) {
        predicate = m -> m == null || m.size() == size;

        addCheck("sizeOf", predicate);

        return this;
    }

    public MapSchema required() {
        predicate = Objects::nonNull;

        addCheck("required", predicate);

        return this;
    }

    public MapSchema shape(Map<String, ? extends BaseSchema<?>> schemas) {
        if (schemas == null || schemas.isEmpty()) {
            return this;
        }

        for (var entry : schemas.entrySet()) {
            var key = entry.getKey();
            BaseSchema schema = entry.getValue();

            Predicate<Map> check = m -> m.get(key) == null || schema.isValid(m.get(key));

            if (predicate == null) {
                predicate = check;

                continue;
            }

            predicate = predicate.and(check);
        }

        addCheck("shape", predicate);

        return this;
    }
}