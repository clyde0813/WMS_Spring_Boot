package com.example.wms.service;

import com.example.wms.Entity.Product;
import com.example.wms.Entity.Supplier;
import com.example.wms.Exception.DataNotFoundException;
import com.example.wms.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public void create(String name, Integer weight, Integer price, Integer expiration, Supplier supplier) {
        Product product = new Product();
        product.setName(name);
        product.setWeight(weight);
        product.setPrice(price);
        product.setExpiration(expiration);
        product.setSupplier(supplier);
        this.productRepository.save(product);
    }

    public Specification<Product> search(String kw) {
        return new Specification<>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                query.distinct(true);
                return criteriaBuilder.or(criteriaBuilder.like(root.get("name"), "%" + kw + "%"),
                        criteriaBuilder.like(root.get("weight").as(String.class), "%" + kw + "%"),
                        criteriaBuilder.like(root.get("price").as(String.class), "%" + kw + "%"),
                        criteriaBuilder.like(root.get("expiration").as(String.class), "%" + kw + "%"),
                        criteriaBuilder.like(root.join("supplier").get("name").as(String.class), "%" + kw + "%")
                );
            }
        };
    }

    public Page<Product> getList(int page, String kw) {
        Pageable pageable = PageRequest.of(page, 20);
        Specification<Product> spec = search(kw);
        return this.productRepository.findAll(spec, pageable);
    }

    public Product getProduct(Integer id) {
        Optional<Product> product = this.productRepository.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            throw new DataNotFoundException("Product not found");
        }
    }
}
