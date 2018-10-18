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

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ItemManagementApplication {

    private ItemRepository itemRepository;
    private ItemHistoryRepository itemHistoryRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public ItemManagementApplication(ItemRepository itemRepository,
                                     ItemHistoryRepository itemHistoryRepository,
                                     UserRepository userRepository,
                                     RoleRepository roleRepository,
                                     PasswordEncoder passwordEncoder) {
        this.itemRepository = itemRepository;
        this.itemHistoryRepository = itemHistoryRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {

        SpringApplication.run(ItemManagementApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(){
        return args -> {
            // Add Role
            createRoleIfNotFound(Role.RoleName.ADMIN);
            createRoleIfNotFound(Role.RoleName.MANAGER);
            createRoleIfNotFound(Role.RoleName.USER);
            Role adminRole = roleRepository.findByRole(Role.RoleName.ADMIN);
            Role adminUser = roleRepository.findByRole(Role.RoleName.USER);

            // Add users and save these to DB
            User user1 = new User("imamachi", "password", "imamachi@gmail.com");
            User user2 = new User("admin", "password", "admin@gmail.com");
            user1.setPassword(passwordEncoder.encode(user1.getPassword()));
            user2.setPassword(passwordEncoder.encode(user2.getPassword()));
            user1.setRoles(Arrays.asList(adminUser));
            user2.setRoles(Arrays.asList(adminRole, adminUser));
            userRepository.save(user1);
            userRepository.save(user2);

            // Add items and save these to DB
            Item item1 = new Item("S001", "アサヒ ウィルキンソン ジンジャエール 500ml×3本", 1000L);
            Item item2 = new Item("S002", "ダウ゛ ビューティモイスチャー クリーミー泡洗顔料", 1500L);
            Item item3 = new Item("S003", "アリエール 洗濯洗剤 液体 リビングドライイオンパワージェル", 600L);
            itemRepository.save(item1);
            itemRepository.save(item2);
            itemRepository.save(item3);

            Item item4 = new Item("S004", "リケン ふえるわかめちゃん国内 16g", 1000L);
            Item item5 = new Item("S005", "ケロッグ コーンフロスティ 袋 240g", 1000L);
            Item item6 = new Item("S006", "江崎グリコ トマトプリッツ<9袋> 134g", 1000L);
            Item item7 = new Item("S007", "クイックルワイパー フロア用掃除道具 ハンディ 取替用", 1000L);
            Item item8 = new Item("S008", "キッチンクイックル つめかえ用 ジャンボパック 24枚入", 1000L);
            Item item9 = new Item("S009", "エリエール ティッシュ キュート 160組×5箱 パルプ", 1000L);
            itemRepository.save(item4);
            itemRepository.save(item5);
            itemRepository.save(item6);
            itemRepository.save(item7);
            itemRepository.save(item8);
            itemRepository.save(item9);

            // Add itemHistory and save these to DB
            ItemHistoryDetail itemHistoryDetail1 = new ItemHistoryDetail();
            ItemHistoryDetail itemHistoryDetail2 = new ItemHistoryDetail();
            ItemHistoryDetail itemHistoryDetail3 = new ItemHistoryDetail();
            ItemHistoryDetail itemHistoryDetail4 = new ItemHistoryDetail();
            ItemHistoryDetail itemHistoryDetail5 = new ItemHistoryDetail();
            ItemHistoryDetail itemHistoryDetail6 = new ItemHistoryDetail();

            itemHistoryDetail1.setAmount(2L);
            itemHistoryDetail1.setItem(item1);
            itemHistoryDetail2.setAmount(1L);
            itemHistoryDetail2.setItem(item2);
            itemHistoryDetail3.setAmount(3L);
            itemHistoryDetail3.setItem(item3);
            itemHistoryDetail4.setAmount(2L);
            itemHistoryDetail4.setItem(item4);
            itemHistoryDetail5.setAmount(1L);
            itemHistoryDetail5.setItem(item5);
            itemHistoryDetail6.setAmount(3L);
            itemHistoryDetail6.setItem(item6);

            ItemHistory itemHistory1 = new ItemHistory(user1, "test1", Arrays.asList(itemHistoryDetail1, itemHistoryDetail2, itemHistoryDetail3), 5200, java.sql.Date.valueOf(LocalDate.now()));
            itemHistoryDetail1.setItemHistory(itemHistory1);
            itemHistoryDetail2.setItemHistory(itemHistory1);
            itemHistoryDetail3.setItemHistory(itemHistory1);
            itemHistoryRepository.save(itemHistory1);

            ItemHistory itemHistory2 = new ItemHistory(user1, "test1", Arrays.asList(itemHistoryDetail4, itemHistoryDetail5), 3000, java.sql.Date.valueOf(LocalDate.now()));
            itemHistoryDetail4.setItemHistory(itemHistory2);
            itemHistoryDetail5.setItemHistory(itemHistory2);
            itemHistoryRepository.save(itemHistory2);

            ItemHistory itemHistory3 = new ItemHistory(user1, "test1", Arrays.asList(itemHistoryDetail6), 3000, java.sql.Date.valueOf(LocalDate.now()));
            itemHistoryDetail6.setItemHistory(itemHistory3);
            itemHistoryRepository.save(itemHistory3);
        };
    }

    private Role createRoleIfNotFound(Role.RoleName roleName){
        Role role = roleRepository.findByRole(roleName);
        if (role == null) {
            role = new Role(roleName);
            roleRepository.save(role);
        }
        return role;
    }
}
