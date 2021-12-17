package uz.pdp.appwerehouse.dto;

import lombok.Data;

import java.util.Date;

@Data
public class InputDto {

    private Date date;
    private Integer warehouseId;
    private Integer supplierId;
    private Integer currencyId;
    private String factureNumber;

}
