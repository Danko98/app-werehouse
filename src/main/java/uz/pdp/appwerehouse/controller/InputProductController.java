package uz.pdp.appwerehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.dto.InputProductDto;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.InputProduct;
import uz.pdp.appwerehouse.service.InputProductService;

@RestController
@RequestMapping(value = "/inputProduct")
public class InputProductController {


    @Autowired
    InputProductService inputProductService;


//CREATE
    @PostMapping
    public Result addInputProduc(@RequestBody InputProductDto inputProductDto){
        return inputProductService.addInputProduct(inputProductDto);
    }


//UPDATE
    @PutMapping(value = "/{id}")
    public Result editInputProduct(@PathVariable Integer id, @RequestBody InputProductDto inputProductDto){
        return inputProductService.editInputProduct(inputProductDto,id);
    }


//READ ALL
    @GetMapping
    public Page<InputProduct> getInputProductPage(@RequestParam int page){
        return inputProductService.getInputProductPage(page);
    }


//READ BY ID
    @GetMapping(value = "/{id}")
    public Result getInputProductById(@PathVariable Integer id){
        return inputProductService.getInputProductById(id);
    }


//DELETE
    @DeleteMapping(value = "/{id}")
    public Result deleteInputProduct(@PathVariable Integer id){
        return inputProductService.deleteInputProduct(id);
    }



}














