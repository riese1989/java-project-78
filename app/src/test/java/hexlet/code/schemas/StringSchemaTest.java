package hexlet.code.schemas;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class StringSchemaTest {

    @Test
    @DisplayName("Когда у нас отключены проверки")
    void emptyTest() {
        var schema = new StringSchema();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("123"));
    }

    @Test
    @DisplayName("Проверка на null")
    void requiredTest() {
        var schema = new StringSchema();

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid("123"));
    }

    @Test
    @DisplayName("Проверка минимальной длины")
    void minLengthTest() {
        var schema = new StringSchema();
        var minLength = 5;

        schema.minLength(minLength);

        assertFalse(schema.isValid("1234"));
        assertTrue(schema.isValid("12345"));
        assertTrue(schema.isValid("1234567"));
    }

    @Test
    @DisplayName("Проверка содержания подстроки")
    void containsTest() {
        var schema = new StringSchema();
        var subString = "45";

        schema.contains(subString);

        assertFalse(schema.isValid("1234"));
        assertTrue(schema.isValid("12345"));
    }

    @Test
    @DisplayName("Проверка цепочки")
    void concatenationTest() {
        var schema = new StringSchema();
        var minLength = 5;
        var subString = "45";

        schema.required().minLength(minLength).contains(subString);

        assertFalse(schema.isValid("45"));
        assertFalse(schema.isValid("1234"));
        assertTrue(schema.isValid("12345"));
    }
}