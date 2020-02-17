package com.mx.learn.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Properties;

/**
 * Created by geely
 */
public class PropertiesUtil {

    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static Properties props;

    static {
        String filename = "mmall.properties";
        props = new Properties();
        try {
            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(filename)));
        } catch (IOException e) {
            logger.error("加载配置文件一次", e);
        }
    }

    public static String getProperty(String key) {
        String value = props.getProperty(key.trim());
        if (StringUtils.isNotBlank(value)) {
            return value.trim();
        }
        return null;
    }

    public static String getProperty(String key, String defaultValue) {
        String value = props.getProperty(key.trim());
        if (StringUtils.isNotBlank(value)) {
            return value.trim();
        }
        return defaultValue;
    }
}
