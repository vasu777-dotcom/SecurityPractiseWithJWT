package com.example.SecurityPractiseWithJWT.repo;

import com.example.SecurityPractiseWithJWT.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User,String> {

    @Query("select u from User u where u.username=:username")
    Optional<User> getByUsername(String username);

    GetRoles findByUsername(String username);

    interface GetRoles{
        Set<String> getRoles();
    }
}
