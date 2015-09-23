package pe.jiyoung.toy.spring.common.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class ToyPropertyResolver extends PropertyPlaceholderConfigurer{

    private String externalConfRoot;
    private static Map<String, String> propertiesMap = new HashMap<String, String>();

    public void setExternalConfRoot(final String externalConfRoot) throws FileNotFoundException, IOException {
        final Resource confHome = ToyResourceLoader.getResource(externalConfRoot);
        this.externalConfRoot = confHome.getFile().getAbsolutePath();;
    }

    @Override
    public void setLocations(final Resource... locations) {
        final int cnt = locations.length;
        final Resource[] newLocations = new Resource[cnt];
        int i = 0;

        for(final Resource l : locations){
            final String propertyFilePath = this.externalConfRoot + "/" + l.getFilename();
            final Resource newResource = new FileSystemResource(new File(propertyFilePath));
            newLocations[i++] = newResource;
        }
        super.setLocations(newLocations);
    }

    @Override
    protected void processProperties(final ConfigurableListableBeanFactory beanFactory, final Properties properties) {
        super.processProperties(beanFactory, properties);
        for (final Object propertyKey : properties.keySet()) {
            propertiesMap.put(propertyKey.toString(), this.resolvePlaceholder(propertyKey.toString(), properties));
        }
    }

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
