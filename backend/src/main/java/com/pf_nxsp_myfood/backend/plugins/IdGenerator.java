package com.pf_nxsp_myfood.backend.plugins;

import java.util.UUID;

public class IdGenerator {
    public static String generate() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String generateWithLength(Integer length) {
        length = length == null ? 20 : length;
        return generate().substring(0, length);
    }
}
