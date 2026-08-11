package com.example.common.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SkuUtilsTest {
    @Test
    void normalize() {
        assertEquals("ABC-1", SkuUtils.normalize(" abc-1 "));
    }

    @Test
    void nullThrows() {
        assertThrows(IllegalArgumentException.class, () -> SkuUtils.normalize(null));
    }
}