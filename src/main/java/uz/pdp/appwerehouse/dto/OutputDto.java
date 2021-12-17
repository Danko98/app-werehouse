package uz.pdp.appwerehouse.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OutputDto {
    private Date date;
    private Integer warehouseId;
    private Integer currencyId;
    private Integer clientId;
    private String factureNumber;
}
