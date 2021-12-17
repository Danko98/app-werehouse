package uz.pdp.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.Client;
import uz.pdp.appwerehouse.repository.ClientRepository;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Result addClient(Client clientDto){
        if (clientRepository.existsByNameAndPhoneNumber(clientDto.getName(),clientDto.getPhoneNumber())){
            return new Result("Bunday client ro'yxatda mavjud",false);
        }
        Client client = new Client();
        client.setPhoneNumber(clientDto.getPhoneNumber());
        client.setName(clientDto.getName());
        client.setActive(true);
        clientRepository.save(client);
        return new Result("Client saqlandi",true);
    }

    public Result editClient(Client clientDto, Integer id, boolean active){
        if (clientRepository.existsById(id)){
            return new Result("Bunday client ro'yxatda mavjud emas",false);
        }
        if (clientRepository.existsByNameAndPhoneNumber(clientDto.getName(),clientDto.getPhoneNumber())){
            return new Result("Bunday client ro'yxatda mavjud",false);
        }

        Optional<Client> optionalClient = clientRepository.findById(id);
        Client client = optionalClient.get();
        client.setActive(active);
        client.setName(clientDto.getName());
        client.setPhoneNumber(clientDto.getPhoneNumber());
        clientRepository.save(client);

        return new Result("Client tahrirlandi",true);

    }

    public Page<Client> getClientPage(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return clientRepository.findAll(pageable);
    }

    public Result getClientById(Integer id){
        if (clientRepository.existsById(id)){
            return new Result("Bunday client ro'yxatda mavjud emas",false);
        }
        Optional<Client> optionalClient = clientRepository.findById(id);
        return new Result(optionalClient.get());
    }

    public Result deleteClient(Integer id){
        if (clientRepository.existsById(id)){
            return new Result("Bunday client ro'yxatda mavjud emas",false);
        }
        clientRepository.deleteById(id);
        return new Result("Client ro'yxatdan o'chirildi",true);
    }

}
