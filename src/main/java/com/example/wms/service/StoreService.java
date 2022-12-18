package com.example.wms.service;

import com.example.wms.Entity.Store;
import com.example.wms.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public Page<Store> getList(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return this.storeRepository.findAll(pageable);
    }
}
