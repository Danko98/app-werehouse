package uz.pdp.appwerehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.appwerehouse.dto.CategoryDto;
import uz.pdp.appwerehouse.dto.Result;
import uz.pdp.appwerehouse.entity.Category;
import uz.pdp.appwerehouse.entity.Measurement;
import uz.pdp.appwerehouse.repository.CategoryRepository;
import uz.pdp.appwerehouse.repository.MeasurementRepository;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public Result addCategoryRepository(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null){
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalCategory.isPresent()) {
                return new Result("Bunday ota kategorya mavjud emas", false);
            }
                category.setParenCategory(optionalCategory.get());
            }
        return new Result("Categorya saqlandi", true);
        }

    public Page<Category> findAllBy(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return categoryRepository.findAllBy(pageable);
    }

    public Page<Category> getByParentCategoryId(Integer categoryId, int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return categoryRepository.findAllByParenCategory_Id(categoryId,pageable);
    }

    public Category getCategoryById(Integer id){
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.get();
    }

    public Result editCategory(Integer id, CategoryDto categoryDto){

//SHU ID LIK CATEGORYA BORMI
        if (categoryRepository.existsById(id)){
            return new Result("Bunday kategorya topilmadi.",false);
        }

//SHUNGA OXSHASH CATEGORYA BORMI
        if (categoryRepository.existsByNameAndParenCategory_Id(categoryDto.getName(),categoryDto.getParentCategoryId())){
            return new Result("Bu kategorya mavjud",false);
        }

//OTA KATEGORYA BORMI
        Optional<Category> optionalCategory1 = categoryRepository.findById(id);
        Category category = optionalCategory1.get();

        if (categoryDto.getParentCategoryId() != null){
            Optional<Category> optionalParentCategory = categoryRepository.findById(categoryDto.getParentCategoryId());
            if (!optionalParentCategory.isPresent()) {
                return new Result("Bunday ota kategorya mavjud emas", false);
            }
            category.setParenCategory(optionalParentCategory.get());
        }

//KATEGORYA O'ZGARTIRIB SAQLANDI
        category.setName(categoryDto.getName());
        categoryRepository.save(category);

        return new Result("Categorya saqlandi", true);

    }

    public Result deleteCategory(Integer id){
        if (!categoryRepository.existsById(id)){
            return new Result("Bunday kategorya topilmadi",false);
        }
        categoryRepository.deleteById(id);
        return new Result("Kategorya o'chirildi",true);
    }
}



