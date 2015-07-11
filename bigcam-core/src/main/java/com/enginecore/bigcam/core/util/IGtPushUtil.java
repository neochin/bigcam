package com.enginecore.bigcam.core.util;

import com.gexin.rp.sdk.template.TransmissionTemplate;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

/**
 * Created by yyam on 15-7-11.
 */
@Resource
public class IGtPushUtil {
    @Value("${bigcam.getui.appId}")
    public static String appId;

    @Value("${bigcam.getui.appKey}")
    public static String appKey;


    public static TransmissionTemplate transmissionTemplate(String content) {
        return transmissionTemplate(content, 2);
    }

    public static TransmissionTemplate transmissionTemplateWithOpenApp(String content) {
        return transmissionTemplate(content, 1);
    }

    private static TransmissionTemplate transmissionTemplate(String content, Integer transmissionType) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(transmissionType);
        template.setTransmissionContent(content);
        return template;
    }
}
