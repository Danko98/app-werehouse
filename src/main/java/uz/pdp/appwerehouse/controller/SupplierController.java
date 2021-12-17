package uz.pdp.appwerehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.Supplier;
import uz.pdp.appwerehouse.service.SupplierService;

@RestController
@RequestMapping(value = "/supplier")
public class SupplierController {

    @Autowired
    SupplierService supplierService;


//CREATE
    @PostMapping
    public Result addSupplier(@RequestBody Supplier supplierDto){
        return supplierService.addSupplier(supplierDto);
    }

//READ ALL
    @GetMapping
    private Page<Supplier> getSupplierPage(@RequestParam int page){
        return supplierService.getSupplierPage(page);
    }

//READ BY ID
    @GetMapping(value = "/{id}")
    public Result getSupplierById(@PathVariable Integer id){
        return supplierService.getSupplierById(id);
    }

//UPDATE
    @PutMapping(value = "/{id}")
    public Result editSupplier(@PathVariable Integer id, @RequestBody Supplier supplierDto,@RequestParam boolean active ){
        return supplierService.editSupplier(id,supplierDto,active);
    }

//DELETE
    @DeleteMapping(value = "/{id}")
    public Result deleteSupplier(@PathVariable Integer id){
        return supplierService.deleteSupplier(id);
    }


}
