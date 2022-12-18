package com.example.wms.controller;

import com.example.wms.Entity.Product;
import com.example.wms.Entity.Store;
import com.example.wms.Entity.Supplier;
import com.example.wms.repository.ProductRepository;
import com.example.wms.repository.StoreRepository;
import com.example.wms.repository.SupplierRepository;
import com.example.wms.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final StoreService storeService;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;

    @GetMapping("/")
    public String main(Model model, @RequestParam(value = "store_page", defaultValue = "0") int page) {
        Page<Store> storePage = this.storeService.getList(page);
        Integer storeSize = this.storeRepository.findAll().size();
        Integer productSize = this.productRepository.findAll().size();
        Integer supplierSize = this.supplierRepository.findAll().size();
        model.addAttribute("storePage", storePage);
        model.addAttribute("store_size", storeSize);
        model.addAttribute("product_size", productSize);
        model.addAttribute("supplier_size", supplierSize);
        return "system/main";
    }

    @GetMapping("/product")
    public String Product(Model model) {
        List<Product> productList = this.productRepository.findAll();
        model.addAttribute("product_list", productList);
        return "system/product";
    }

    @GetMapping("/mypage")
    public String Mypage(Model model, Principal principal) {
        List<Store> storeList = this.storeRepository.findAllByUser_email(principal.getName());
        System.out.println(storeList);
        model.addAttribute("store_list", storeList);
        return "system/mypage";
    }
}
