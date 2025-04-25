package org.jong.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] arg){
        ApplicationContext applicationContext = SpringApplication.run(Application.class, arg);

        SandBox reactorSandBox = applicationContext.getBean(SandBox.class);
        reactorSandBox.start();

    }
}
