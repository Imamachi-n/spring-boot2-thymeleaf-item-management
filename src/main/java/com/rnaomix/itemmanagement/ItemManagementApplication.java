package com.rnaomix.itemmanagement;

import com.rnaomix.itemmanagement.model.User;
import com.rnaomix.itemmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ItemManagementApplication {

    private UserRepository userRepository;

    @Autowired
    public ItemManagementApplication(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public static void main(String[] args) {

        SpringApplication.run(ItemManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(){
        return args -> {
            // Add users and save these to DB
            User user1 = new User("imamachi", "password", "USER");
            User user2 = new User("admin", "password", "ADMIN");
            userRepository.save(user1);
            userRepository.save(user2);
        };
    }
}
