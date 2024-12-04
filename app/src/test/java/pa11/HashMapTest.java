package pa11;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HashMapTest {

    private HashMap map;

    @BeforeEach
    void setUp() {
        map = new HashMap();
    }

    @Test
    void testConstructor() {
        assertNotNull(map);
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }

    @Test
    void testPutAndGet() {
        assertNull(map.put("key1", "value1"));
        assertEquals("value1", map.get("key1"));
        assertEquals(1, map.size());

        assertEquals("value1", map.put("key1", "newValue1"));
        assertEquals("newValue1", map.get("key1"));
        assertEquals(1, map.size());

        assertNull(map.put("key2", "value2"));
        assertEquals("value2", map.get("key2"));
        assertEquals(2, map.size());
    }

    @Test
    void testRemove() {
        map.put("key1", "value1");
        map.put("key2", "value2");

        assertEquals("value1", map.remove("key1"));
        assertNull(map.get("key1"));
        assertEquals(1, map.size());

        assertNull(map.remove("key3"));
        assertEquals(1, map.size());

        assertEquals("value2", map.remove("key2"));
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }

    @Test
    void testIsEmpty() {
        assertTrue(map.isEmpty());
        map.put("key1", "value1");
        assertFalse(map.isEmpty());
        map.remove("key1");
        assertTrue(map.isEmpty());
    }

    @Test
    void testKeySet() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        String[] keys = map.keySet();
        assertEquals(3, keys.length);
        assertTrue(contains(keys, "key1"));
        assertTrue(contains(keys, "key2"));
        assertTrue(contains(keys, "key3"));
    }

    @Test
    void testValues() {
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");

        String[] values = map.values();
        assertEquals(3, values.length);
        assertTrue(contains(values, "value1"));
        assertTrue(contains(values, "value2"));
        assertTrue(contains(values, "value3"));
    }

    @Test
    void testPutAndGetWithCollisions() {
        map.put("Aa", "value1");
        map.put("BB", "value2"); 

        assertEquals("value1", map.get("Aa"));
        assertEquals("value2", map.get("BB"));

        assertEquals(2, map.size());
    }

    @Test
    void testRemoveWithCollisions() {
        map.put("Aa", "value1");
        map.put("BB", "value2");

        assertEquals("value1", map.remove("Aa"));
        assertNull(map.get("Aa"));
        assertEquals("value2", map.get("BB"));

        assertEquals(1, map.size());
    }

    @Test
    void testUpdateValue() {
        map.put("key1", "value1");
        map.put("key1", "newValue1");

        assertEquals("newValue1", map.get("key1"));
        assertEquals(1, map.size());
    }

    @Test
    void testGetNonExistentKey() {
        map.put("key1", "value1");
        assertNull(map.get("key2"));
    }

    @Test
    void testRemoveNonExistentKey() {
        assertNull(map.remove("key1"));
        assertEquals(0, map.size());
    }

    @Test
    void testGetFromEmptyMap() {
        assertNull(map.get("key1"));
    }

    private boolean contains(String[] array, String value) {
        for (String s : array) {
            if (s.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
