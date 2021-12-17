package uz.pdp.appwerehouse.dto;

import lombok.Data;

@Data
public class CategoryDto {
    private String name;
    private Integer parentCategoryId;

}
