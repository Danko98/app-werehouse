package uz.pdp.appwerehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.dto.OutputDto;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.Output;
import uz.pdp.appwerehouse.service.OutputService;

@RestController
@RequestMapping
public class OutputController {


    @Autowired
    OutputService outputService;

//CREATE
    @PostMapping
    public Result addOutput(@RequestBody OutputDto outputDto){
        return outputService.addOutput(outputDto);
    }


//UPDATE
    @PutMapping(value = "/{id}")
    public Result editOutput(@RequestBody OutputDto outputDto, @PathVariable Integer id){
        return outputService.editOutput(id,outputDto);
    }


//READ ALL
    @GetMapping
    public Page<Output> getOutputPage(@RequestParam int page){
        return outputService.getOutputPage(page);
    }


//READ BY ID
    @GetMapping(value = "/{id}")
    public Result getOutputById(@PathVariable Integer id){
        return outputService.getOutputById(id);
    }


//DELETE
    @DeleteMapping(value = "/{id}")
    public Result deleteOutput(@PathVariable Integer id){
        return outputService.deleteOutput(id);
    }




}



















