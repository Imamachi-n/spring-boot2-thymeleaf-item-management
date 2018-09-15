package com.rnaomix.itemmanagement;

import com.rnaomix.itemmanagement.model.Item;
import com.rnaomix.itemmanagement.model.ItemHistory;
import com.rnaomix.itemmanagement.model.ItemHistoryDetail;
import com.rnaomix.itemmanagement.model.User;
import com.rnaomix.itemmanagement.repository.ItemHistoryRepository;
import com.rnaomix.itemmanagement.repository.ItemRepository;
import com.rnaomix.itemmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class ItemManagementApplication {

    private UserRepository userRepository;
    private ItemRepository itemRepository;
    private ItemHistoryRepository itemHistoryRepository;

    @Autowired
    public ItemManagementApplication(UserRepository userRepository, ItemRepository itemRepository, ItemHistoryRepository itemHistoryRepository){
        this.userRepository = userRepository;
        this.itemRepository = itemRepository;
        this.itemHistoryRepository = itemHistoryRepository;
    }

    public static void main(String[] args) {

        SpringApplication.run(ItemManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(){
        return args -> {
            // Add users and save these to DB
            User user1 = new User("imamachi", "password", User.Role.USER);
            User user2 = new User("admin", "password", User.Role.ADMIN);
            userRepository.save(user1);
            userRepository.save(user2);

            // Add items and save these to DB
            Item item1 = new Item("S001", "Coffee", 1000L);
            Item item2 = new Item("S002", "Tea", 1500L);
            itemRepository.save(item1);
            itemRepository.save(item2);

            // Add itemHistory and save these to DB
            ItemHistoryDetail itemHistoryDetail1 = new ItemHistoryDetail();
            ItemHistoryDetail itemHistoryDetail2 = new ItemHistoryDetail();
            ItemHistoryDetail itemHistoryDetail3 = new ItemHistoryDetail();
            ItemHistory itemHistory1 = new ItemHistory(user1, "test1", Arrays.asList(itemHistoryDetail1, itemHistoryDetail2, itemHistoryDetail3));

            itemHistoryDetail1.setAmount(1000L);
            itemHistoryDetail1.setItemHistory(itemHistory1);
            itemHistoryDetail1.setItem(item1);
            itemHistoryDetail2.setAmount(1500L);
            itemHistoryDetail2.setItemHistory(itemHistory1);
            itemHistoryDetail2.setItem(item2);
            itemHistoryDetail3.setAmount(3000L);
            itemHistoryDetail3.setItemHistory(itemHistory1);
            itemHistoryDetail3.setItem(item1);

            itemHistoryRepository.save(itemHistory1);
        };
    }
}