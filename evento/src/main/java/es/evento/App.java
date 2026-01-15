package es.evento;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ConfigFiesta.class);
        Fiesta fiesta = context.getBean(Fiesta.class);

        fiesta.celebrar();
        fiesta.terminar();
    }
}