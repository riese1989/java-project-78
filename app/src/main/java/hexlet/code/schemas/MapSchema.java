package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map> {
    public MapSchema sizeof(int size) {
        predicate = m -> m == null || m.size() == size;

        checks.put("sizeOf", predicate);

        return this;
    }

    public MapSchema required() {
        predicate = s -> s != null && !s.isEmpty();

        checks.put("required", predicate);

        return this;
    }

    public MapSchema shape(Map<String, ? extends BaseSchema<?>> schemas) {

        for (var entry : schemas.entrySet()) {
            var key = entry.getKey();
            var schema = entry.getValue();
            Predicate<Map> check = m -> m.get(key) == null || schema.isValid(m.get(key));

            if(predicate == null) {
                predicate = check;
            }

            predicate.and(check);
        }

        checks.put("shape", predicate);

        return this;
    }
}
