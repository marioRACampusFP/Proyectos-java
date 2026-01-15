package org.example;


import org.springframework.context.support.ClassPathXmlApplicationContext;



public class Principal {
    public static void main(String[] args) {


        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("context.xml")) {


            Coche c = (Coche) context.getBean("coche");
            c.arrancar();
        }
    }
}
