package com.example.wms.service;

import com.example.wms.Entity.Product;
import com.example.wms.Entity.Supplier;
import com.example.wms.Exception.DataNotFoundException;
import com.example.wms.repository.SupplierRepository;
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
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public Supplier getSupplier(Integer id) {
        Optional<Supplier> supplier = this.supplierRepository.findById(id);
        if (supplier.isPresent()) {
            return supplier.get();
        } else {
            throw new DataNotFoundException("Supplier not found");
        }
    }

    public Specification<Supplier> search(String kw) {
        return new Specification<>() {
            @Override
            public Predicate toPredicate(Root<Supplier> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                query.distinct(true);
                return criteriaBuilder.or(criteriaBuilder.like(root.get("name"), "%" + kw + "%"),
                        criteriaBuilder.like(root.get("license"), "%" + kw + "%"),
                        criteriaBuilder.like(root.get("owner"), "%" + kw + "%"),
                        criteriaBuilder.like(root.get("location"), "%" + kw + "%")
                );
            }
        };
    }

    public Page<Supplier> getList(int page, String kw) {
        Pageable pageable = PageRequest.of(page, 20);
        Specification<Supplier> spec = search(kw);
        return this.supplierRepository.findAll(spec, pageable);
    }
}
