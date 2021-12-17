package uz.pdp.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.Measurement;
import uz.pdp.appwerehouse.repository.MeasurementRepository;

import java.util.Optional;

@Service
public class MeasurementService {

    @Autowired
    MeasurementRepository measurementRepository;

  public Result addMeasurementService(Measurement measurementDto){
      if (!measurementRepository.existsByName(measurementDto.getName())) {

          measurementRepository.save(measurementDto);
          return new Result("O'lchov birligi saqlandi",true);
      }else
          return new Result("Bunday o'chov birligi mavjud", false);
  }

  public Page<Measurement> getMeasurementPageService(@RequestParam int page){
      Pageable pageable = PageRequest.of(page, 10);
      return measurementRepository.findAllBy(pageable);
  }

  public Result editMeasurementService(Integer id, Measurement measurementDto){

      if (measurementRepository.existsById(id)){
            if (measurementRepository.existsByName(measurementDto.getName())){
                Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
                Measurement measurement = optionalMeasurement.get();
                measurement.setName(measurementDto.getName());
                measurementRepository.save(measurement);
                return new Result("O'lchov birligi o'zgartirildi.",true);

            }else
                return new Result("Bunday o'lchov birligi mavjud.", false);
      }else
          return new Result("Bunday o'lchov birligi topilmadi.", false);
  }

  public Result deleteMeasurementService(Integer id){
      if (measurementRepository.existsById(id)){
          measurementRepository.deleteById(id);
          return new Result("O'lchov birligi o'chirildi.", true);
      }else
          return new Result("Bunday o'lchov birligi topilmadi.", false);  }

}
