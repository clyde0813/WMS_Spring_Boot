package com.example.wms.repository;

import com.example.wms.Entity.Store;
import com.example.wms.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    List<Store> findAllByUser_email(String email);

    Page<Store> findAll(Pageable pageable);
}
