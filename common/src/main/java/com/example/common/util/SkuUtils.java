package com.example.common.util;

public final class SkuUtils {
    private SkuUtils() {
    }

    public static String normalize(String sku) {
        if (sku == null) {
            throw new IllegalArgumentException("sku required");
        }
        return sku.trim().toUpperCase();
    }
}