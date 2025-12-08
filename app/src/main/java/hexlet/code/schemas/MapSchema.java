package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map> {
    public MapSchema sizeof(int size) {
        predicate = m -> m == null || m.size() == size;

        addCheck("sizeOf", predicate);

        expression += ".sizeOf(" + size + ")";

        return this;
    }

    public MapSchema required() {
        predicate = s -> s != null && !s.isEmpty();

        addCheck("required", predicate);

        expression += ".required()";

        return this;
    }

    public MapSchema shape(Map<String, ? extends BaseSchema<?>> schemas) {
        if (schemas == null || schemas.isEmpty()) {
            return this;
        }

        for (var entry : schemas.entrySet()) {
            var key = entry.getKey();
            var schema = entry.getValue();

            System.out.println("-----------------------");
            System.out.println(key);
            System.out.println(schema.expression);
            Predicate<Map> check = m -> m.get(key) == null || schema.isValid(m.get(key));

            if(predicate == null) {
                predicate = check;
            }

            predicate.and(check);
        }

        addCheck("shape", predicate);

        return this;
    }

    @Override
    protected void exp() {
        expression = "new MapSchema()";
    }
}
