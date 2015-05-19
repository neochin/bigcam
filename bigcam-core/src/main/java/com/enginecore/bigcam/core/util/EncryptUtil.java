package com.enginecore.bigcam.core.util;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by yyam on 14-11-4.
 */
public class EncryptUtil {
    public static String md5(String srcValue) {
        if (StringUtils.isEmpty(srcValue)) {
            return srcValue;
        }
        String md5Result = "";
        try {
            md5Result = Base64.encodeBase64String(DigestUtils.md5(srcValue.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return md5Result;
    }
}
