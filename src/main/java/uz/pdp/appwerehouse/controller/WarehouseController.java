package uz.pdp.appwerehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.Warehouse;
import uz.pdp.appwerehouse.service.WarehouseService;

@RestController
@RequestMapping(value = "/warehouse")
public class WarehouseController {

    @Autowired
    WarehouseService warehouseService;


//CREATE
    @PostMapping
    public Result addWare(@RequestBody Warehouse warehouseDto){
        return warehouseService.addWare(warehouseDto);
    }


//UPDATE
    @PutMapping(value = "/{id}")
    public Result editWare(@PathVariable Integer id,@RequestBody Warehouse warehouseDto,@RequestParam boolean active){
        return warehouseService.editWare(warehouseDto, id, active);
    }


//READ ALL
    @GetMapping
    public Page<Warehouse> getWarePage(@RequestParam int page){
        return warehouseService.getWarePage(page);
    }


//READ BY ID
    @GetMapping(value = "/{id}")
    public Result getWareById(@PathVariable Integer id){
        return warehouseService.getWareById(id);
    }


//DELETE
    @DeleteMapping(value = "/{id}")
    public Result deleteWare(@PathVariable Integer id){
        return warehouseService.deleteWare(id);
    }

}










