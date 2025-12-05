package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map, MapSchema> {
    private int size;
    private Map<Object, BaseSchema> schemas;

    public MapSchema sizeof(int newSize) {
        size = newSize;

        return this;
    }

    public  MapSchema shape(Map newSchemas) {
        schemas = newSchemas;

        return this;
    }

    @Override
    public boolean isVerified(Map map) {
        if (isRequired() && map == null) {
            return false;
        }

        if (map == null || size == 0) {
            return true;
        }

        if (schemas != null) {
            for (var entry : schemas.entrySet()) {
                var checkedKey = entry.getKey();
                var schema = entry.getValue();
                var checkedValue = map.get(checkedKey);

                if (map.get(checkedKey) == null) {
                    continue;
                }

                if (!schema.isValid(checkedValue)) {
                    return false;
                }
            }
        }

        return map.size() == size;
    }
}
