package org.jong.webflux;

import org.jong.webflux.reactor.ReactorSandBox;
import org.jong.webflux.reative.ReactiveSandBox;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] arg){
        ApplicationContext applicationContext = SpringApplication.run(Application.class, arg);

        SandBox reactorSandBox = applicationContext.getBean(ReactiveSandBox.class);
        reactorSandBox.start();

    }
}
