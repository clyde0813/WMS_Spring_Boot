package com.example.wms.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "wms_product")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255, nullable = false)
    private String name;

    @Column(length = 255, nullable = false)
    private Integer weight;

    @Column(length = 255, nullable = false)
    private Integer price;

    @Column(length = 255, nullable = false)
    private Integer expiration;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
}
