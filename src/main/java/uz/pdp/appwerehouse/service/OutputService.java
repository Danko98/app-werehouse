package uz.pdp.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwerehouse.dto.OutputDto;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.Client;
import uz.pdp.appwerehouse.entity.Currency;
import uz.pdp.appwerehouse.entity.Output;
import uz.pdp.appwerehouse.entity.Warehouse;
import uz.pdp.appwerehouse.repository.ClientRepository;
import uz.pdp.appwerehouse.repository.CurrencyRepository;
import uz.pdp.appwerehouse.repository.OutputRepository;
import uz.pdp.appwerehouse.repository.WarehouseRepository;

import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
public class OutputService {

    @Autowired
    OutputRepository outputRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    WarehouseRepository warehouseRepository;


    public Result addOutput(OutputDto outputDto){
        if (warehouseRepository.existsById(outputDto.getWarehouseId())){
            return new Result("Bunday ombor topilmadi",false);
        }

        if (clientRepository.existsById(outputDto.getClientId())){
            return new Result("Bunday klent topilmadi",false);
        }

        if (currencyRepository.existsById(outputDto.getCurrencyId())){
            return new Result("Bunday valyuta topilmadi",false);
        }

        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());

        UUID code = UUID.randomUUID();

        Output output = new Output();
        output.setClient(optionalClient.get());
        output.setCurrency(optionalCurrency.get());
        output.setWarehouse(optionalWarehouse.get());
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setDate((Timestamp) outputDto.getDate());
        output.setCode(code.toString());
        outputRepository.save(output);

        return new Result("Chiqim saqlandi",true);
    }

    public Result editOutput(Integer id,OutputDto outputDto){
        if (outputRepository.existsById(id)){
            return new Result("Bunday chiqim topilmadi.",false);
        }

        if (warehouseRepository.existsById(outputDto.getWarehouseId())){
            return new Result("Bunday ombor topilmadi",false);
        }

        if (clientRepository.existsById(outputDto.getClientId())){
            return new Result("Bunday klent topilmadi",false);
        }

        if (currencyRepository.existsById(outputDto.getCurrencyId())){
            return new Result("Bunday valyuta topilmadi",false);
        }

        Optional<Output> optionalOutput = outputRepository.findById(id);
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(outputDto.getWarehouseId());
        Optional<Currency> optionalCurrency = currencyRepository.findById(outputDto.getCurrencyId());
        Optional<Client> optionalClient = clientRepository.findById(outputDto.getClientId());

        UUID code = UUID.randomUUID();

        Output output = optionalOutput.get();
        output.setClient(optionalClient.get());
        output.setCurrency(optionalCurrency.get());
        output.setWarehouse(optionalWarehouse.get());
        output.setFactureNumber(outputDto.getFactureNumber());
        output.setDate((Timestamp) outputDto.getDate());
        output.setCode(code.toString());
        outputRepository.save(output);

        return new Result("Chiqim tahrirlandi",true);
    }

    public Page<Output> getOutputPage(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return outputRepository.findAll(pageable);
    }

    public Result getOutputById(Integer id){
        if (outputRepository.existsById(id)){
            return new Result("Bunday chiqim topilmadi.",false);
        }
        Optional<Output> optionalOutput = outputRepository.findById(id);
        return new Result(optionalOutput.get());
    }

    public Result deleteOutput(Integer id){
        if (outputRepository.existsById(id)){
            return new Result("Bunday chiqim topilmadi.",false);
        }
        outputRepository.deleteById(id);
        return new Result("Chiqim o'chirildi",true);
    }





}








