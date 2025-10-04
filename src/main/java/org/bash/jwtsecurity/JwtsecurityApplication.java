package org.bash.jwtsecurity;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtsecurityApplication {

//    // Load .env into environment
//    Dotenv dotenv = Dotenv.load();
//        System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
//        System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));

    public static void main(String[] args) {
        SpringApplication.run(JwtsecurityApplication.class, args);
    }
//    @PostConstruct
//    public void checkEnv() {
//        System.out.println("DB_USERNAME from Spring: " + System.getenv("DB_USERNAME"));
//    }

}
