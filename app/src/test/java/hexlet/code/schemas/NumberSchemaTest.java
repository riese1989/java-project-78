package hexlet.code.schemas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {
    @Test
    @DisplayName("Когда у нас отключены проверки")
    void emptyTest() {
        var schema = new NumberSchema();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(123));
    }

    @Test
    @DisplayName("Проверка на null")
    void requiredTest() {
        var schema = new NumberSchema();

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(123));
    }

    @Test
    @DisplayName("Проверка позитивный/непозитивный")
    void positiveTest() {
        var schema = new NumberSchema();

        assertTrue(schema.isValid(-1));
        assertTrue(schema.isValid(0));
        assertTrue(schema.isValid(1));

        schema.positive();

        assertFalse(schema.isValid(-1));
        assertFalse(schema.isValid(0));
        assertTrue(schema.isValid(1));
    }

    @Test
    @DisplayName("Проверка диапозона")
    void rangeTest() {
        var schema = new NumberSchema();
        var minRange = 0;
        var maxRange = 10;

        schema.range(minRange, maxRange);

        assertFalse(schema.isValid(minRange - 1));
        assertTrue(schema.isValid(minRange));
        assertTrue(schema.isValid((minRange + maxRange) / 2));
        assertTrue(schema.isValid(maxRange));
        assertFalse(schema.isValid(maxRange + 1));
    }

    @Test
    @DisplayName("Проверка цепочки")
    void concatenationTest() {
        var schema = new NumberSchema();
        var minRange = 0;
        var maxRange = 10;

        schema.required().positive().range(minRange, maxRange);


        assertFalse(schema.isValid(minRange - 1));
        assertFalse(schema.isValid(minRange));
        assertTrue(schema.isValid((minRange + maxRange) / 2));
        assertTrue(schema.isValid(maxRange));
        assertFalse(schema.isValid(maxRange + 1));
    }
}
