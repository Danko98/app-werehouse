package uz.pdp.appwerehouse.dto;

import lombok.Data;

@Data
public class OutputProductDto {

    private Integer productId;
    private Integer outputId;
    private Double price;
    private Double amount;

}
