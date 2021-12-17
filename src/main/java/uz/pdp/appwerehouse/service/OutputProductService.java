package uz.pdp.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwerehouse.dto.OutputProductDto;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.Output;
import uz.pdp.appwerehouse.entity.OutputProduct;
import uz.pdp.appwerehouse.entity.Product;
import uz.pdp.appwerehouse.repository.OutputProductRepository;
import uz.pdp.appwerehouse.repository.OutputRepository;
import uz.pdp.appwerehouse.repository.ProductRepository;

import java.util.Optional;

@Service
public class OutputProductService {

    @Autowired
    OutputProductRepository outputProductRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OutputRepository outputRepository;

    public Result addOutProduct(OutputProductDto outputProductDto){
        if (outputRepository.existsById(outputProductDto.getOutputId())){
            return new Result("Bunday chiqim topilmadi.",false);
        }

        if (productRepository.existsById(outputProductDto.getProductId())){
            return new Result("Bunday mahsulot topilmadi.",false);
        }

        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());

        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());

        return new Result("Mahsulot chiqimi saqlandi.",true);

    }

    public Result editOutProduct(Integer id, OutputProductDto outputProductDto){
        if (!outputProductRepository.existsById(id)){
            return new Result("Bunday mahsulot chiqimi topilmadi.", false);
        }

        if (!outputRepository.existsById(outputProductDto.getOutputId())){
            return new Result("Bunday chiqim topilmadi.",false);
        }

        if (!productRepository.existsById(outputProductDto.getProductId())){
            return new Result("Bunday mahsulot topilmadi.",false);
        }

        Optional<Product> optionalProduct = productRepository.findById(outputProductDto.getProductId());
        Optional<Output> optionalOutput = outputRepository.findById(outputProductDto.getOutputId());

        OutputProduct outputProduct = new OutputProduct();
        outputProduct.setAmount(outputProductDto.getAmount());
        outputProduct.setPrice(outputProductDto.getPrice());
        outputProduct.setProduct(optionalProduct.get());
        outputProduct.setOutput(optionalOutput.get());

        return new Result("Mahsulot chiqimi saqlandi.",true);

    }

    public Page<OutputProduct> getOutProductPage(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return outputProductRepository.findAll(pageable);
    }

    public Result getOutProductById(Integer id){
        if (!outputProductRepository.existsById(id)){
            return new Result("Bunday mahsulot chiqimi topilmadi.",false);
        }
        Optional<OutputProduct> optionalOutputProduct = outputProductRepository.findById(id);
        return new Result(optionalOutputProduct.get());
    }

    public Result deleteOutProduct(Integer id){
        if (!outputProductRepository.existsById(id)){
            return new Result("Bunday mahsulot chiqimi topilmadi.",false);
        }
        outputProductRepository.deleteById(id);
        return new Result("Mahsulot chiqimi o'chirildi.",true);
    }












}
