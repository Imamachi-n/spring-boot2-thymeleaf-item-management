package com.rnaomix.itemmanagement.service;

import com.rnaomix.itemmanagement.form.UserForm;
import com.rnaomix.itemmanagement.model.Role;
import com.rnaomix.itemmanagement.model.User;
import com.rnaomix.itemmanagement.repository.RoleRepository;
import com.rnaomix.itemmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.*;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> getUserById(long userId){

        return userRepository.findById(userId);
    }

    @Override
    public List<UserForm> getUserList(){
        List<UserForm> userFormList = new ArrayList<>();

        userRepository.findAll().forEach(user -> {
            String auth = user.getRoles().stream().reduce(
                    "",
                    (tmp, role) -> tmp + ", " + role.getRole().name(),
                    (tmp1, tmp2) -> tmp1 + tmp2
                ).substring(1);
            userFormList.add(new UserForm(user.getUserId(), user.getUsername(), user.getEmail(),
                    user.getFirstName(), user.getLastName(), auth));
        });
        return userFormList;
    }

    @Override
    public User findUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void saveUser(User user, boolean isAdmin){
        // BCryptで暗号化してパスワードとして保存
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (isAdmin){
            user.setRoles(Arrays.asList(roleRepository.findByRole(Role.RoleName.ADMIN),
                                        roleRepository.findByRole(Role.RoleName.USER)));
        }else{
            user.setRoles(Arrays.asList(roleRepository.findByRole(Role.RoleName.USER)));
        }

        userRepository.save(user);
    }
}
