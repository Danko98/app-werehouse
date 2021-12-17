package uz.pdp.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.Warehouse;
import uz.pdp.appwerehouse.repository.WarehouseRepository;

import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    WarehouseRepository warehouseRepository;

    public Result addWare(Warehouse warehouseDto){
        if (warehouseRepository.existsByName(warehouseDto.getName())){
            return new Result("Bunday ombor ro'yxatda mavjud",false);
        }
        Warehouse warehouse = new Warehouse();
        warehouse.setName(warehouseDto.getName());
        warehouse.setActive(true);

        warehouseRepository.save(warehouse);
        return new Result("Ombor saqlandi",true);
    }

    public Result editWare(Warehouse warehouseDto,Integer id, boolean active){

        if (warehouseRepository.existsById(id)){
            return new Result("Bunday ombor topilmadi",false);
        }
        if (warehouseRepository.existsByName(warehouseDto.getName())){
            return new Result("Bunday ombor mavjud",false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        Warehouse warehouse = optionalWarehouse.get();
        warehouse.setName(warehouseDto.getName());
        warehouse.setActive(active);
        warehouseRepository.save(warehouse);
        return new Result("Ombor saqlandi",true);
    }

    public Page<Warehouse> getWarePage(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return warehouseRepository.findAll(pageable);
    }

    public Result getWareById(Integer id){
        if (warehouseRepository.existsById(id)){
            return new Result("Bunday ombor ro'yxatda mavjud emas",false);
        }
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        return new Result(optionalWarehouse.get());
    }

    public Result deleteWare(Integer id){
        if (warehouseRepository.existsById(id)){
            return new Result("Bunday ombor ro'yxatda mavjud emas",false);
        }
        warehouseRepository.deleteById(id);
        return new Result("Ombor ro'yxatdan o'chirildi");
    }


}
