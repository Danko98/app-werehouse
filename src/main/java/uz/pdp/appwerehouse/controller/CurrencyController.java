package uz.pdp.appwerehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.Currency;
import uz.pdp.appwerehouse.service.CurrencyService;

import javax.naming.InsufficientResourcesException;

@RestController
@RequestMapping(value = "/currency")
public class CurrencyController {

    @Autowired
    CurrencyService currencyService;


//CREATE
    @PostMapping
    public Result addCurrency(@RequestBody Currency currencyDto){
            return currencyService.addCurrency(currencyDto);
    }


//UPDATE
    @PutMapping(value = "/{id}")
    public Result editCurrency(@RequestParam Integer id,Currency currencyDto,@RequestParam boolean active){
        return currencyService.editCurrency(id, currencyDto, active);
    }


//READ ALL
    @GetMapping
    public Page<Currency> getCurrencyPage(@RequestParam int page){
        return currencyService.getCurrencyPage(page);
    }


//READ BY ID
    @GetMapping(value = "/{id}")
    public Result getCurrencyById(@PathVariable Integer id){
        return currencyService.getCurrencyById(id);
    }


//DELETE
    @DeleteMapping(value = "/{id}")
    public Result deleteCurrency(@PathVariable Integer id){
        return currencyService.deletCurrency(id);
    }



}
