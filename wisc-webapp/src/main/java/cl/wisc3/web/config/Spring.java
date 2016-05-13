package cl.wisc3.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Spring {
    private static final Logger LOGGER = LoggerFactory.getLogger(Spring.class);

    private static ApplicationContext applicationContext;

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        LOGGER.info("Inicializado");
    }

    public static <T> T bean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    public static <T> Object bean(String name) {
        return applicationContext.getBean(name);
    }

}
