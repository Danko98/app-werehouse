package uz.pdp.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwerehouse.dto.InputDto;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.Currency;
import uz.pdp.appwerehouse.entity.Input;
import uz.pdp.appwerehouse.entity.Supplier;
import uz.pdp.appwerehouse.entity.Warehouse;
import uz.pdp.appwerehouse.repository.CurrencyRepository;
import uz.pdp.appwerehouse.repository.InputRepository;
import uz.pdp.appwerehouse.repository.SupplierRepository;
import uz.pdp.appwerehouse.repository.WarehouseRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.UUID;

@Service
public class InputService {

    @Autowired
    InputRepository inputRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    WarehouseRepository warehouseRepository;

    @Autowired
    SupplierRepository supplierRepository;

    public Result addInput(InputDto inputDto){
        if (!currencyRepository.existsById(inputDto.getCurrencyId())){
            return new Result("Bunday valyuta topilmadi",false);
        }

        if (!supplierRepository.existsById(inputDto.getSupplierId())){
            return new Result("Bunday yetkazib beruvchi topilmadi",false);
        }

        if (!warehouseRepository.existsById(inputDto.getWarehouseId())){
            return new Result("Bunday ombor topilmadi",false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        UUID code = UUID.randomUUID();

        Input input = new Input();
        input.setCurrency(optionalCurrency.get());
        input.setSupplier(optionalSupplier.get());
        input.setWarehouse(optionalWarehouse.get());
        input.setDate((Timestamp) inputDto.getDate());
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setCode(code.toString());
        inputRepository.save(input);
        return new Result("Kirim saqlandi",true);

    }

    public Result editInput(Integer id,InputDto inputDto){
        if (!inputRepository.existsById(id)){
            return new Result("Bunday kirim topilmadi",false);
        }

        if (!currencyRepository.existsById(inputDto.getCurrencyId())){
            return new Result("Bunday valyuta topilmadi",false);
        }

        if (!supplierRepository.existsById(inputDto.getSupplierId())){
            return new Result("Bunday yetkazib beruvchi topilmadi",false);
        }

        if (!warehouseRepository.existsById(inputDto.getWarehouseId())){
            return new Result("Bunday ombor topilmadi",false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(inputDto.getWarehouseId());
        Optional<Currency> optionalCurrency = currencyRepository.findById(inputDto.getCurrencyId());
        Optional<Supplier> optionalSupplier = supplierRepository.findById(inputDto.getSupplierId());
        Optional<Input> optionalInput = inputRepository.findById(id);
        UUID code = UUID.randomUUID();

        Input input = optionalInput.get();
        input.setCurrency(optionalCurrency.get());
        input.setSupplier(optionalSupplier.get());
        input.setWarehouse(optionalWarehouse.get());
        input.setDate((Timestamp) inputDto.getDate());
        input.setFactureNumber(inputDto.getFactureNumber());
        input.setCode(code.toString());
        inputRepository.save(input);
        return new Result("Kirim tahrirlandi",true);

    }

    public Page<Input> getInputPage(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return inputRepository.findAll(pageable);
    }

    public Result getInputById(Integer id){
        if (!inputRepository.existsById(id)){
            return new Result("Bunday kirim topilmadi.",false);
        }
        Optional<Input> optionalInput = inputRepository.findById(id);
        return new Result(optionalInput.get());
    }

    public Result deleteInput(Integer id){
        if (!inputRepository.existsById(id)){
            return new Result("Bunday kirim topilmadi.",false);
        }
        inputRepository.deleteById(id);
        return new Result("Kirim o'chirildi",true);
    }


}










