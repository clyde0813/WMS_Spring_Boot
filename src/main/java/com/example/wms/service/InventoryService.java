package com.example.wms.service;

import com.example.wms.Entity.Inventory;
import com.example.wms.Entity.Product;
import com.example.wms.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    public Specification<Inventory> search(String kw) {
        return new Specification<>() {
            @Override
            public Predicate toPredicate(Root<Inventory> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                query.distinct(true);
                return criteriaBuilder.or(
                        criteriaBuilder.like(root.join("store").get("name").as(String.class), "%" + kw + "%"),
                        criteriaBuilder.like(root.join("product").get("name").as(String.class), "%" + kw + "%")
                );
            }
        };
    }

    public Page<Inventory> getList(int page, String kw) {
        Pageable pageable = PageRequest.of(page, 20);
        Specification<Inventory> spec = search(kw);
        return this.inventoryRepository.findAll(spec, pageable);
    }
}
