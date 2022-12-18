package com.example.wms.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "store")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String location;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
