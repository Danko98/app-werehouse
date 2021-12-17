package uz.pdp.appwerehouse.dto;

import lombok.Data;

import java.util.Date;

@Data
public class InputProductDto {

    private Integer productId;
    private Integer inputId;
    private Double price;
    private Date expireDate;
    private Double amount;

}
