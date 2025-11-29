package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchemaRequired<MapSchema> implements BaseSchema<Map> {
    private int size;
    private Map<Object, BaseSchema> schemas;

    public MapSchema sizeof(int size) {
        this.size = size;

        return this;
    }

    public MapSchema shape(Map schemas) {
        this.schemas = schemas;

        return this;
    }

    @Override
    public boolean isValid(Map map) {
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
