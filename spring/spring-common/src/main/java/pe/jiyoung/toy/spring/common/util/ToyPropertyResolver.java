package pe.jiyoung.toy.spring.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class ToyPropertyResolver extends PropertyPlaceholderConfigurer {

    private static Map<String, String> propertiesMap = new HashMap<String, String>();

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties properties) {
        super.processProperties(beanFactory, properties);
        for (final Object propertyKey : properties.keySet()) {
            propertiesMap.put(propertyKey.toString(), this.resolvePlaceholder(propertyKey.toString(), properties));
        }
    }

    public static String getProperty(String name) {
        return propertiesMap.get(name);
    }

    public static String setProperty(String name, String value) {
        return propertiesMap.put(name, value);
    }

    public static boolean getPropertyAsBoolean(final String key) {
        return Boolean.valueOf(propertiesMap.get(key));
    }

    public static int getPropertyAsInt(final String key) {
        return Integer.valueOf(propertiesMap.get(key));
    }

    public static Properties loadProperties(final String fileName) throws FileNotFoundException, IOException {
        final Resource resource = ToyResourceLoader.getResource(fileName);
        return PropertiesLoaderUtils.loadProperties(resource);
    }


}
