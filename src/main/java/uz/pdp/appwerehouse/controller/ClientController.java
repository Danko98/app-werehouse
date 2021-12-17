package uz.pdp.appwerehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.Client;
import uz.pdp.appwerehouse.service.ClientService;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    ClientService clientService;


//CREATE
    @PostMapping
    public Result addClient(@RequestBody Client clientDto){
        return clientService.addClient(clientDto);
    }


//UPDATE
    @PutMapping(value = "/{id}")
    public Result editClient(@PathVariable Integer id,@RequestBody Client clientDto,@RequestParam boolean active){
        return clientService.editClient(clientDto, id, active);
    }


//READ ALL
    @GetMapping
    public Page<Client> getClientPage(@RequestParam int page){
        return clientService.getClientPage(page);
    }


//READ BY ID
    @GetMapping(value = "/{id}")
    public Result getClientById(@PathVariable Integer id){
        return clientService.getClientById(id);
    }


//DELETE
    @DeleteMapping(value = "/{id}")
    public Result deleteClient(@PathVariable Integer id){
        return clientService.deleteClient(id);
    }


}
