package com.rnaomix.itemmanagement.repository;

import com.rnaomix.itemmanagement.model.Item;
import com.rnaomix.itemmanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // TodoRepository#findAll
    // TodoRepository#findById
    // TodoRepository#save
    // TodoRepository#deleteById
    @Modifying
    @Query("DELETE FROM User WHERE userId IN (:userIds)")
    int deleteUserByUserId(@Param("userIds") List<Integer> userIds);

    User findByUsername(String username);

    User findByUserId(Integer userId);
}
