package uz.pdp.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.appwerehouse.dto.ProductDto;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.Attachment;
import uz.pdp.appwerehouse.entity.Category;
import uz.pdp.appwerehouse.entity.Measurement;
import uz.pdp.appwerehouse.entity.Product;
import uz.pdp.appwerehouse.repository.AttachmentRepository;
import uz.pdp.appwerehouse.repository.CategoryRepository;
import uz.pdp.appwerehouse.repository.MeasurementRepository;
import uz.pdp.appwerehouse.repository.ProductRepository;

import java.awt.geom.RectangularShape;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    MeasurementRepository measurementRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    public Result addProduct(ProductDto productDto){

        //KATEGORYA TEKSHIRISH
        if (productRepository.existsByNameAndCategoryId(productDto.getName(),productDto.getCategoryId())) {
            return new Result("Bunday kategorya mavjud", false);
        }

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());

        //KATEGORYA TEKSHIRISH
        if (!optionalCategory.isPresent()){
            return new Result("Bunday kategorya mavjud emas",false);
        }

        //PHOTO TEKSHIIRISH
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent()) {
            return new Result("Bunday resm mavjud emas", false);
        }

        //MEASUREMENT TEKSHIRISH
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalAttachment.isPresent()){
            return new Result("Bunday o'lchov birligi mavjud emas", false);
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setCode("1");
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());

        productRepository.save(product);
        return new Result("Mahsulot saqlandi",true);

    }

    public Result getProductById(Integer id){
        if (!productRepository.existsById(id)){
            return new Result("Bunday mahsulot mavjud emas",false);
        }
        Optional<Product> optionalProduct = productRepository.findById(id);
        return new Result(optionalProduct.get());
    }

    public Page<Product> getProductAll(int page){
        Pageable pageable = PageRequest.of(page, 10);
        return productRepository.findAllBy(pageable);
    }

    public Page<Product> getProductAllByName(String name, int page){
        Pageable pageable = PageRequest.of(page, 10);
        return productRepository.findAllByName(name, pageable);
    }

    public Page<Product> getProductAllByCategoryId(Integer id, int page){
        Pageable pageable = PageRequest.of(page,10);
        return productRepository.findAllByCategory_Id(id,pageable);
    }

    public Page<Product> getProductAllByMeasurementId(Integer id, int page){
        Pageable pageable = PageRequest.of(page,10);
        return productRepository.findAllByMeasurement_Id( id,pageable);
    }

    public Result editProduct(ProductDto productDto,Integer id){

        //PRODUCTNI TEKSHIRISH
        if (productRepository.existsById(id)){
            return new Result("Bunday mahsulot topilmadi",false);
        }

        //KATEGORYA TEKSHIRISH
        if (productRepository.existsByNameAndCategoryId(productDto.getName(),productDto.getCategoryId())) {
            return new Result("Bunday kategorya mavjud", false);
        }

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());

        //KATEGORYA TEKSHIRISH
        if (!optionalCategory.isPresent()){
            return new Result("Bunday kategorya mavjud emas",false);
        }

        //PHOTO TEKSHIIRISH
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (!optionalAttachment.isPresent()) {
            return new Result("Bunday resm mavjud emas", false);
        }

        //MEASUREMENT TEKSHIRISH
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (!optionalAttachment.isPresent()){
            return new Result("Bunday o'lchov birligi mavjud emas", false);
        }

        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.get();

        product.setMeasurement(optionalMeasurement.get());
        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setName(productDto.getName());
        productRepository.save(product);

        return new Result("Mahsulot o'zgartirildi",true);

    }

    public Result deleteProductById(Integer id) {
        if (productRepository.existsById(id)){
            return new Result("Bunday mahsulot mavjud emas", false);
        }
        productRepository.deleteById(id);
        return new Result("Mahsulot o'chirildi",true);
    }
}








