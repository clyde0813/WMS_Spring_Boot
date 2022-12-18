package com.example.wms.Entity;

import lombok.*;

import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "auth_user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255, nullable = false, unique = true)
    private String username;

    @Column(length = 255, nullable = false, unique = true)
    @Email
    private String email;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;


}


