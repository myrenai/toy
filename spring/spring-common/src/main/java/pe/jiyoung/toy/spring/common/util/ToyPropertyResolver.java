package pe.jiyoung.toy.spring.common.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class ToyPropertyResolver {

    private List<String> locations;

    public void setLocations(final List<String> lacations) throws IOException {
        this.locations = lacations;
        for(final String path : this.locations){
            final Resource resource = ToyResourceLoader.getResource(path);
            final Properties properties = new Properties();
            properties.load(resource.getInputStream());
            for (final Object propertyKey : properties.keySet()) {
                propertiesMap.put(propertyKey.toString(), properties.getProperty((String) propertyKey));
            }
        }
    }

    private static Map<String, String> propertiesMap = new HashMap<String, String>();

    public static String getProperty(final String name) {
        return propertiesMap.get(name);
    }

    public static String setProperty(final String name, final String value) {
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
