package com.example.wms.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "supplier")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private String license;

    @Column(length = 255, nullable = false)
    private String location;

    @Column(length = 255, nullable = false)
    private String owner;
}
