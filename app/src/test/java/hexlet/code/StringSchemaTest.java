package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class StringSchemaTest {

    @Test
    void requiredTest() {
        var schema = new StringSchema();

        schema.required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid("123"));
    }

    @Test
    void minLengthTest() {
        var schema = new StringSchema();
        var minLength = 5;

        schema.minLength(minLength);

        assertFalse(schema.isValid("1234"));
        assertTrue(schema.isValid("12345"));
        assertTrue(schema.isValid("1234567"));
    }

    @Test
    void containsTest() {
        var schema = new StringSchema();
        var subString = "45";

        schema.contains(subString);

        assertFalse(schema.isValid("1234"));
        assertTrue(schema.isValid("12345"));
    }

    @Test
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