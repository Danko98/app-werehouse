package uz.pdp.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.Supplier;
import uz.pdp.appwerehouse.repository.SupplierRepository;

import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    public Result addSupplier(Supplier supplierDto){
        if (supplierRepository.existsByNameAndPhoneNumber(supplierDto.getName(),supplierDto.getPhoneNumber())){
            return new Result("Bunday yetkazib beruvchi mavjud",false);
        }
        Supplier supplier = new Supplier();
        supplier.setName(supplierDto.getName());
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());
        supplier.setActive(true);
        supplierRepository.save(supplier);

        return new Result("Yetkazib beruvchi saqlandi",true);
    }

    public Page<Supplier> getSupplierPage(int page){
        Pageable pageable = PageRequest.of(page,10);
        return supplierRepository.findAll(pageable);
    }

    public Result getSupplierById(Integer id){
        if (supplierRepository.existsById(id)){
            return new Result("Bunday yetkazib beruvchi mavjud emas",false);
        }

        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        return new Result(optionalSupplier.get());
    }

    public Result editSupplier(Integer id, Supplier supplierDto, boolean active){
        if (supplierRepository.existsById(id)){
            return new Result("Bunday yetkazib beruvchi topilmadi",false);
        }

        if (supplierRepository.existsByNameAndPhoneNumber(supplierDto.getName(),supplierDto.getPhoneNumber())){
            return new Result("Bunday yetkazib beruvchi mavjud",false);
        }

        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        Supplier supplier = optionalSupplier.get();
        supplier.setActive(active);
        supplier.setName(supplierDto.getName());
        supplier.setPhoneNumber(supplierDto.getPhoneNumber());
        supplierRepository.save(supplier);

        return new Result("Yetkazib beruvchi tahrirlandi",true);
    }

    public Result deleteSupplier(Integer id){
        if (supplierRepository.existsById(id)){
            return new Result("Bunday yetkazib beruvchi topilmadi",false);
        }

        supplierRepository.deleteById(id);
        return new Result("Yetkazib beruvchi o'chirildi",true);
    }


}
