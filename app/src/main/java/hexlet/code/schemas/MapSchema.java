package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchemaRequired<MapSchema> implements BaseSchema<Map>{
    private int size;

    public MapSchema sizeOf(int size) {
        this.size = size;

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

        return map.size() == size;
    }
}
