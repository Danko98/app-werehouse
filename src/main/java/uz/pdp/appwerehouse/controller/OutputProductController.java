package uz.pdp.appwerehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.dto.OutputProductDto;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.OutputProduct;
import uz.pdp.appwerehouse.service.OutputProductService;

@RestController
@RequestMapping("/outputProduct")
public class OutputProductController {

    @Autowired
    OutputProductService outputProductService;

//CREATE
    @PostMapping
    public Result addOutProduct(OutputProductDto outputProductDto){
        return outputProductService.addOutProduct(outputProductDto);
    }

//UPDATE
    @PutMapping(value = "/{id}")
    public Result editOutProduct(@PathVariable Integer id, @RequestBody OutputProductDto outputProductDto){
        return outputProductService.editOutProduct(id, outputProductDto);
    }

//READ ALL
    @GetMapping
    public Page<OutputProduct> getOutProduct(@RequestParam int page){
        return outputProductService.getOutProductPage(page);
    }

//READ BY ID
    @GetMapping(value = "/{id}")
    public Result getOutProductById(@PathVariable Integer id){
        return outputProductService.getOutProductById(id);
    }

//DELETE
    @DeleteMapping(value = "/{id}")
    public Result deleteOutProduct(@PathVariable Integer id){
        return outputProductService.deleteOutProduct(id);
    }



}
