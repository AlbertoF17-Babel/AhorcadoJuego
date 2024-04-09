package es.babel;

import es.babel.applications.AhorcadoApp;
import es.babel.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AhorcadoRunner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        AhorcadoApp ahorcado = context.getBean(AhorcadoApp.class);
        ahorcado.run();
    }
}