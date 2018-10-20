package com.rnaomix.itemmanagement;

import com.rnaomix.itemmanagement.model.*;
import com.rnaomix.itemmanagement.repository.ItemHistoryRepository;
import com.rnaomix.itemmanagement.repository.ItemRepository;
import com.rnaomix.itemmanagement.repository.RoleRepository;
import com.rnaomix.itemmanagement.repository.UserRepository;
import com.rnaomix.itemmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ItemManagementApplication {

    public static void main(String[] args) {

        SpringApplication.run(ItemManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(){
        return args -> {
            // do something
        };
    }

}
