package com.example.wms.Entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "wms_inventory")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(length = 255, nullable = false)
    private Integer volume;
}
