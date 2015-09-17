package pe.jiyoung.toy.spring.common.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class ToyResourceLoader {
    public static Resource getResource(final String path) {
        if (path.startsWith("classpath:")) {
            final String filePath = path.substring(10);
            return new ClassPathResource(filePath);
        } else if (path.startsWith("file:")) {
            final String filePath = path.substring(5);
            return new FileSystemResource(filePath);
        } else {
            throw new IllegalArgumentException("You want the resource in the classpath or file path? (classpath: or file: mandantory)");
        }
    }
}
