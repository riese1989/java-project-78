package hexlet.code.schemas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MapSchemaTest {
    @Test
    @DisplayName("Когда у нас отключены проверки")
    void emptyTest() {
        var schema = new MapSchema();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(Map.of("1","2","3","4")));
    }

    @Test
    @DisplayName("Проверка на null")
    void requiredTest() {
        var schema = new MapSchema();

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(Map.of("1","2","3","4")));
    }

    @Test
    @DisplayName("Проверка на размер")
    void sizeOfTest() {
        var schema = new MapSchema();
        var size = 2;

        schema.sizeOf(size);

        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(Map.of()));
        assertTrue(schema.isValid(Map.of("1","2","3","4")));
        assertFalse(schema.isValid(Map.of("1","2","3","4", "5", "6")));
    }

    @Test
    @DisplayName("Проверка цепочки")
    void concatenationTest() {
        var schema = new MapSchema();
        var size = 2;

        schema.required().sizeOf(size);

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(Map.of()));
        assertTrue(schema.isValid(Map.of("1","2","3","4")));
        assertFalse(schema.isValid(Map.of("1","2","3","4", "5", "6")));
    }

    @Test
    @DisplayName("Проверка shape")
    void shapeTest() {
        var schema = new MapSchema();
        var schemasMap = Map.of(
                "name", new StringSchema().required().minLength(2).contains("ad").minLength(1),
                "age", new NumberSchema().required().positive().range(10, 20)
        );
        var checkedMap = Map.of(
                "name", "Vlad",
                "age", 15
        );

        schema.shape(schemasMap);

        assertTrue(schema.isValid(checkedMap));
    }
}