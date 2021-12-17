package uz.pdp.appwerehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.dto.InputDto;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.Input;
import uz.pdp.appwerehouse.service.InputService;


@RestController
@RequestMapping(value = "/input")
public class InputController {


    @Autowired
    InputService inputService;


//CREATE
    @PostMapping
    public Result addInput(@RequestBody InputDto inputDto){
        return inputService.addInput(inputDto);
    }


//UPDATE
    @PutMapping(value = "/{id}")
    public Result editInput(@RequestBody InputDto inputDto, @PathVariable Integer id){
        return inputService.editInput(id, inputDto);
    }


//READ ALL
    @GetMapping
    public Page<Input> getInputPage(@RequestParam int page){
        return inputService.getInputPage(page);
    }


//READ BY ID
    @GetMapping(value = "/{id}")
    public Result getInputById(@PathVariable Integer id){
        return inputService.getInputById(id);
    }


//DELETE
    @DeleteMapping(value = "/{id}")
    public Result deleteInput(@PathVariable Integer id){
        return inputService.deleteInput(id);
    }


}











