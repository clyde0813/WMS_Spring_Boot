package com.example.wms.controller;

import com.example.wms.Entity.Inventory;
import com.example.wms.Entity.Product;
import com.example.wms.Entity.Store;
import com.example.wms.Entity.Supplier;
import com.example.wms.repository.InventoryRepository;
import com.example.wms.repository.ProductRepository;
import com.example.wms.repository.StoreRepository;
import com.example.wms.repository.SupplierRepository;
import com.example.wms.service.InventoryService;
import com.example.wms.service.ProductService;
import com.example.wms.service.StoreService;
import com.example.wms.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {

    private final StoreService storeService;
    private final ProductService productService;
    private final SupplierService supplierService;
    private final InventoryService inventoryService;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final InventoryRepository inventoryRepository;

    @GetMapping("/")
    public String main(Model model, @RequestParam(value = "store_page", defaultValue = "0") int page, Principal principal) {
        Page<Store> storePage = this.storeService.getList(page);
        Integer storeSize = this.storeRepository.findAll().size();
        Integer productSize = this.productRepository.findAll().size();
        Integer supplierSize = this.supplierRepository.findAll().size();
        Integer mystoreSize = this.storeRepository.findAllByUser_email(principal.getName()).size();
        model.addAttribute("storePage", storePage);
        model.addAttribute("store_size", storeSize);
        model.addAttribute("product_size", productSize);
        model.addAttribute("supplier_size", supplierSize);
        model.addAttribute("mystoreSize", mystoreSize);
        return "system/main";
    }

    @GetMapping("/product")
    public String Product(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "keyword", defaultValue = "") String kw) {
        Page<Product> productPage = this.productService.getList(page, kw);
        List<Supplier> supplierList = this.supplierRepository.findAll();
        model.addAttribute("productPage", productPage);
        model.addAttribute("keyword", kw);
        model.addAttribute("supplierList", supplierList);
        return "system/product";
    }

    @PostMapping("/product")
    public String Product(Model model, @RequestParam String name, @RequestParam Integer weight, @RequestParam Integer price, @RequestParam Integer expiration, @RequestParam String supplier_id) {
        Supplier supplier = this.supplierService.getSupplier(Integer.valueOf(supplier_id));
        this.productService.create(name, weight, price, expiration, supplier);
        return "redirect:/product";
    }

    @GetMapping("/inventory")
    public String Inventory(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "keyword", defaultValue = "") String kw, Principal principal) {
        Page<Inventory> inventoryPage = this.inventoryService.getList(page, kw);
        model.addAttribute("inventoryPage", inventoryPage);
        return "system/inventory";
    }

    @GetMapping("/supplier")
    public String Supplier(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "keyword", defaultValue = "") String kw) {
        Page<Supplier> supplierPage = this.supplierService.getList(page, kw);
        model.addAttribute("supplierPage", supplierPage);
        return "system/supplier";
    }

    @GetMapping("/mypage")
    public String Mypage(Model model, Principal principal) {
        List<Store> storeList = this.storeRepository.findAllByUser_email(principal.getName());
        System.out.println(storeList);
        model.addAttribute("store_list", storeList);
        return "system/mypage";
    }
}
