package uz.pdp.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import uz.pdp.appwerehouse.dto.InputDto;
import uz.pdp.appwerehouse.dto.InputProductDto;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.Input;
import uz.pdp.appwerehouse.entity.InputProduct;
import uz.pdp.appwerehouse.entity.Product;
import uz.pdp.appwerehouse.repository.InputProductRepository;
import uz.pdp.appwerehouse.repository.InputRepository;
import uz.pdp.appwerehouse.repository.ProductRepository;

import java.util.Optional;

@Service
public class InputProductService {

    @Autowired
    InputProductRepository inputProductRepository;

    @Autowired
    InputRepository inputRepository;

    @Autowired
    ProductRepository productRepository;



    public Result addInputProduct(InputProductDto inputProductDto){

        if (inputRepository.existsById(inputProductDto.getInputId())){
            return new Result("Bunday kirim topilmadi",false);
        }
        if (productRepository.existsById(inputProductDto.getProductId())){
            return new Result("Bunday mahsulot topilmadi",false);
        }

        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        InputProduct inputProduct = new InputProduct();

        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());

        inputProductRepository.save(inputProduct);
        return new Result("Mahsulot kirimga saqlandi",true);
    }

    public Result editInputProduct(InputProductDto inputProductDto, Integer id){

        if (inputRepository.existsById(inputProductDto.getInputId())){
            return new Result("Bunday kirim topilmadi",false);
        }

        if (productRepository.existsById(inputProductDto.getProductId())){
            return new Result("Bunday mahsulot topilmadi",false);
        }

        if (inputProductRepository.existsById(id)){
            return new Result("Bunday kirim mahsulot topilmadi",false);
        }

        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        Optional<Input> optionalInput = inputRepository.findById(inputProductDto.getInputId());
        Optional<Product> optionalProduct = productRepository.findById(inputProductDto.getProductId());
        InputProduct inputProduct = optionalInputProduct.get();

        inputProduct.setProduct(optionalProduct.get());
        inputProduct.setInput(optionalInput.get());
        inputProduct.setAmount(inputProductDto.getAmount());
        inputProduct.setPrice(inputProductDto.getPrice());
        inputProduct.setExpireDate(inputProductDto.getExpireDate());

        inputProductRepository.save(inputProduct);
        return new Result("Mahsulot tahrirlandi",true);
    }

    public Page<InputProduct> getInputProductPage(int page){
        Pageable pageable = PageRequest.of(page,10);
        return inputProductRepository.findAll(pageable);
    }

    public Result getInputProductById(Integer id){
        if (inputProductRepository.existsById(id)){
            return new Result("Bunday kirim mahsulot topilmadi",false);
        }
        Optional<InputProduct> optionalInputProduct = inputProductRepository.findById(id);
        return new Result(optionalInputProduct.get());
    }

    public Result deleteInputProduct(Integer id){
        if (inputProductRepository.existsById(id)){
            return new Result("Bunday kirim mahsulot topilmadi",false);
        }
        inputProductRepository.deleteById(id);
        return new Result("Mahsulot o'chirildi",true);
    }

}






