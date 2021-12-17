package uz.pdp.appwerehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.Measurement;
import uz.pdp.appwerehouse.repository.MeasurementRepository;
import uz.pdp.appwerehouse.service.MeasurementService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/measurement")
public class MeasurementController {

    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    MeasurementService measurementService;


//CREATE
    @PostMapping
    public Result addMeasurementController(@RequestBody Measurement measurementDto){
        return measurementService.addMeasurementService(measurementDto);
    }


//READ PAGEABLE ALL
    @GetMapping
    public Page<Measurement> getMeasurementPage(@RequestParam int page){
        return measurementService.getMeasurementPageService(page);
    }


//READ BY ID
    @GetMapping(value = "/{id}")
    public Measurement getMeasurementById(@PathVariable Integer id){
        if (measurementRepository.existsById(id)){
            Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
            return optionalMeasurement.get();
        }else
            return new Measurement();
    }


//EDIT
    @PutMapping(value = "/{id}")
    public Result editMeasurementController(@PathVariable Integer id,@RequestBody Measurement measurementDto){
        return measurementService.editMeasurementService(id,measurementDto);
    }


//DELETE
    @DeleteMapping(value = "/{id}")
    public Result deleteMeasurementController(@PathVariable Integer id){
        return measurementService.deleteMeasurementService(id);
    }


}


















