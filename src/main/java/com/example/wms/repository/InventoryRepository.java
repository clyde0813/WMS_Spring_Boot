package com.example.wms.repository;

import com.example.wms.Entity.Inventory;
import com.example.wms.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    Page<Inventory> findAll(Specification<Inventory> spec,Pageable pageable);

//    Page<Inventory> findAllByStoreUserEmail(String email, Specification<Inventory> spec, Pageable pageable);
}
