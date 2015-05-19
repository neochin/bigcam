package com.enginecore.bigcam.core.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by yyam on 14-11-8.
 */
public enum Gender {
    MAN("man"), WOMAN("woman"), PRIVATE("private"), UNKNOWN("");

    private String gender;
    
    Gender(String gender) {
        this.gender = gender;
    }

    public static Gender match(String gender) {
        if (StringUtils.isBlank(gender)) {
            return UNKNOWN;
        }
        for (Gender g : Gender.values()) {
            if (g.gender.equals(gender)) {
                return g;
            }
        }
        return UNKNOWN;
    }

    @Override
    public String toString() {
        return this.gender;
    }
}
