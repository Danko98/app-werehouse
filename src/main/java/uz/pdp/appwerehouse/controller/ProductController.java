package uz.pdp.appwerehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.dto.ProductDto;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.Product;
import uz.pdp.appwerehouse.service.ProductService;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    ProductService productService;

//CREATE
    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto){
        return productService.addProduct(productDto);
    }


//READ ALL
    @GetMapping
    public Page<Product> getProductPage(@RequestParam int page){
        return productService.getProductAll(page);
    }


//READ BY ID
    @GetMapping(value = "/{id}")
    public Result getProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }


//READ BY NAME
    @GetMapping(value = "/byName/{name}")
    public Page<Product> getProductPageByName(@PathVariable String name, @RequestParam int page){
        return productService.getProductAllByName(name, page);
    }


//READ BY CATEGORY ID
    @GetMapping(value = "/byCategoryId/{id}")
    public Page<Product> getProductPageByCategoryId(@PathVariable Integer id, @RequestParam int page){
        return productService.getProductAllByCategoryId(id,page);
    }


//READ BY MEASUREMENT ID
    @GetMapping(value = "/byMeasurementId/{id}")
    public Page<Product> getProductPageByMeasurementId(@PathVariable Integer id, @RequestParam int page){
        return productService.getProductAllByMeasurementId(id,page);
    }


//EDIT
    @PutMapping(value = "/{id}")
    public Result editProduct(@PathVariable Integer id, @RequestBody ProductDto productDto){
        return productService.editProduct(productDto,id);
    }


//DELETE
    @DeleteMapping(value = "/{id}")
    public Result deleteProduct(@PathVariable Integer id){
        return productService.deleteProductById(id);
    }



}
