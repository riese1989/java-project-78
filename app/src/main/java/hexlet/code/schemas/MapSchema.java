package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchemaRequired<MapSchema> implements BaseSchema<Map> {
    @SuppressWarnings("HiddenField")
    private int size;
    @SuppressWarnings("HiddenField")
    private Map<Object, BaseSchema> schemas;

    public final MapSchema sizeof(int size) {
        this.size = size;

        return this;
    }

    public final MapSchema shape(Map schemas) {
        this.schemas = schemas;

        return this;
    }

    @Override
    public final boolean isValid(Map map) {
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
