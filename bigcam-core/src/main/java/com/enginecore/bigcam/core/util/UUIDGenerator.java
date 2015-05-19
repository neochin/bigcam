package com.enginecore.bigcam.core.util;

import java.util.UUID;

/**
 * Created by yyam on 14-11-18.
 */
public class UUIDGenerator {
    public static String generate() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        String uuid = generate();
        System.out.println(uuid);
        System.out.println("length:" + uuid.length());
    }
}
