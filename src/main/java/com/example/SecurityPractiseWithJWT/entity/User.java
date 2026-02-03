package com.example.SecurityPractiseWithJWT.entity;

import com.example.SecurityPractiseWithJWT.util.MyCustomId;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Set;

@Entity
@Table(name="user_table")
@Data
public class User {
    @Id
    @MyCustomId
    private String id;
    @Column(name = "username", unique = true)
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "roles_table", joinColumns = @JoinColumn(name="user_id_fk"))
    @NotEmpty
    private Set<String> roles;
}
